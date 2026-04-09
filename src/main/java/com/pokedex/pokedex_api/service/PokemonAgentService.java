package com.pokedex.pokedex_api.service;

import com.google.adk.agents.LlmAgent;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import com.pokedex.pokedex_api.model.Pokemon;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PokemonAgentService {
    private static PokemonService pokemonService;

    public PokemonAgentService(PokemonService pokemonService) {
        PokemonAgentService.pokemonService = pokemonService;
    }

    private final LlmAgent agent = LlmAgent.builder()
            .name("pokedex-advisor")
            .description("A knowledgeable Pokémon advisor")
            .instruction("""
                    You are a knowledgeable Pokédex AI assistant.
                        You help trainers learn about Pokémon and make recommendations.

                        When you need data, use the `lookup_pokemon` tool.
                        Look up maximum 2-3 Pokémon when comparing.

                        IMPORTANT: Keep responses concise and direct.
                        Maximum 3-4 sentences. No markdown formatting.
                        Always address the user as "Trainer".
                        Base recommendations on actual stats you retrieve.
                                        """)
            // offers 10 requests per minute and 500 requests per day on the free tier
            .model("gemini-2.5-flash")
            .tools(FunctionTool.create(PokemonAgentService.class, "lookupPokemon"))
            .build();

    @Schema(name = "lookup_pokemon", description = "Looks up detailed information about a specific Pokémon including its stats, types, and weight")
    public static Map<String, Object> lookupPokemon(
            @Schema(name = "pokemon_name", description = "The name of the Pokémon to look up, lowercase (e.g. 'pikachu', 'charizard')") String pokemonName) {
        try {
            Pokemon pokemon = pokemonService.getPokemon(pokemonName.toLowerCase());

            return Map.of(
                    "name", pokemon.getName(),
                    "id", pokemon.getId(),
                    "types", pokemon.getTypes().stream()
                            .map(t -> t.getType().getName())
                            .toList(),
                    "stats", pokemon.getStats().stream()
                            .map(s -> s.getStat().getName() + ": " + s.getBaseStat())
                            .toList(),
                    "weight", pokemon.getWeight() / 10.0 + "kg");
        } catch (Exception e) {
            return Map.of("error", "Could not find Pokémon: " + pokemonName);
        }
    }

    public String askAgent(String question) {
        try {
            String userId = "default-user";

            // InMemoryRunner manages the agent's session and execution
            InMemoryRunner runner = new InMemoryRunner(agent);

            Session session = runner.sessionService()
                    .createSession(runner.appName(), userId)
                    .blockingGet();

            // Wrap the user's question in ADK's Content/Part structure
            // Content is the message container, Part is the actual text
            Content userMessage = Content.fromParts(
                    Part.fromText(question));
            // Run the agent and collect the response
            AtomicReference<String> response = new AtomicReference<>("");

            Flowable<com.google.adk.events.Event> events = runner.runAsync(userId, session.id(), userMessage);

            events.blockingForEach(event -> {
                if (event.finalResponse()) {
                    String content = event.stringifyContent();
                    if (content != null && !content.isEmpty()) {
                        response.set(content);
                    }
                }
            });
            return response.get().isEmpty()
                    ? "I couldn't find an answer to that question."
                    : response.get();

        } catch (Exception e) {
            return "Sorry, I encountered an error: " + e.getMessage();
        }
    }
}
