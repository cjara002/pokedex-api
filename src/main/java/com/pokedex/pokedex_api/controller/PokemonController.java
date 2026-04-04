package com.pokedex.pokedex_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedex_api.model.PaginatedResponse;
import com.pokedex.pokedex_api.model.Pokemon;
import com.pokedex.pokedex_api.model.PokemonListItem;
import com.pokedex.pokedex_api.service.PokemonService;
import org.springframework.web.bind.annotation.RequestParam;

// @RestController tells Spring "this class handles HTTP requests"
// It combines @Controller and @ResponseBody into one annotation
// Think of this as [ApiController] in .NET
@RestController

// @RequestMapping sets the base URL for all endpoints in this class
// Every endpoint here will start with /api/pokemon
// Think of this as [Route("api/pokemon")] in .NET
@RequestMapping("/api/pokemon")
public class PokemonController {
    // Spring sees @Service on PokemonService and automatically
    // injects it here through constructor injection
    // This is identical in concept to .NET's dependency injection
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // @GetMapping maps HTTP GET requests to this method
    // {name} is a path variable, just like [HttpGet("{name}")] in .NET
    @GetMapping("/{name}")
    public Pokemon getPokemon(@PathVariable String name) {
        // public String getPokemon(@PathVariable String name) {
        // Now the controller just delegates to the service
        // It has no idea HOW the data is fetched - that's the service's job
        return pokemonService.getPokemon(name);
    }

    // @RequestParam maps query parameters from the URL
    // just like [FromQuery] in .NET
    @GetMapping
    public PaginatedResponse<PokemonListItem> getPokemonList(@RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return pokemonService.getPokemonList(limit, offset);
    }

}
