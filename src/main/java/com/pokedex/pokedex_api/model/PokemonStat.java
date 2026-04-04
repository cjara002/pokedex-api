package com.pokedex.pokedex_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonStat {

    @JsonProperty("base_stat")
    private int baseStat;

    private int effort;

    private StatDetail stat;

    @Data
    public static class StatDetail {
        private String name;
        private String url;
    }
}
