package com.pokedex.pokedex_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonSprites {
    // PokéAPI returns this field as "front_default" in the JSON
    // but Java naming convention is camelCase, not snake_case
    // @JsonProperty tells Jackson (Spring's JSON library)  "when you see front_default in the JSON,
    // map it to this frontDefault field" — Jackson is Spring's built-in
    // JSON serializer/deserializer, think of it like System.Text.Json in .NET
    @JsonProperty("front_default")
    private String frontDefault;
}
