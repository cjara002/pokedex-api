package com.pokedex.pokedex_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController tells Spring "this class handles HTTP requests"
// It combines @Controller and @ResponseBody into one annotation
// Think of this as [ApiController] in .NET
@RestController

// @RequestMapping sets the base URL for all endpoints in this class
// Every endpoint here will start with /api/pokemon
// Think of this as [Route("api/pokemon")] in .NET
@RequestMapping("/api/pokemon")
public class PokemonController {

    // @GetMapping maps HTTP GET requests to this method
    // {name} is a path variable, just like [HttpGet("{name}")] in .NET
    @GetMapping("/{name}")
    public String getPokemon(@PathVariable String name) {
        // For now we return a hardcoded string just to prove the server works
        // We'll replace this with a real PokéAPI call in the next sprint
        return "You searched for: " + name;
    }
}
