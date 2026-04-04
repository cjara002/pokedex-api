package com.pokedex.pokedex_api.model;

import lombok.Data;

@Data
public class PokemonType {
    private int slot;
    // This nested class represents the actual type detail
    // with the name like "electric", "fire", "water" etc.
    private TypeDetail type;

    // We define TypeDetail as a static inner class
    // because it only makes sense in the context of PokemonType
    // It's never used anywhere else in our application
    @Data
    public static  class TypeDetail {
        private String name;
        private String url;
    }
}
