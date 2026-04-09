package com.pokedex.pokedex_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.service.PokemonAgentService;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/agent")
public class AgentController {
    private final PokemonAgentService agentService;

    public AgentController(PokemonAgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/ask")
    public Map<String, String> ask(@RequestBody Map<String, String> request) {
        String question = request.get("question");

        if(question == null || question.isBlank()) {
            return Map.of("error", "Please provide a question");
        }

        String answer = agentService.askAgent(question);
        return Map.of("answer", answer);
    }
}
