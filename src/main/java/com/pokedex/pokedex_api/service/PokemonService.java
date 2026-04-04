package com.pokedex.pokedex_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokedex.pokedex_api.model.Pokemon;

@Service
public class PokemonService {

    // RestTemplate is Spring's built-in HTTP client
    // Think of it like HttpClient in .NET or Axios in Angular
    private final RestTemplate restTemplate = new RestTemplate();

    public static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemon(String name) {
    // public String getPokemon(String name) {
        // This makes a real GET request to PokéAPI and returns the raw JSON as a String
        // return restTemplate.getForObject(POKEAPI_URL + name, String.class);
        
        // RestTemplate's getForObject method is doing the heavy lifting here —
        // it fetches the JSON from PokéAPI AND automatically deserializes it
        return restTemplate.getForObject(POKEAPI_URL + name, Pokemon.class);
    }
    
}
