package com.pokedex.pokedex_api.model;

import java.util.List;

import lombok.Data;

// That single @Data annotation tells Lombok to generate all the getters, setters, a constructor, toString,
// equals, and hashCode methods automatically at compile time
@Data
// we only expose exactly what our Angular frontend needs
public class Pokemon {
    private int id;
    private String name;
    private int order;
    private int weight;

    // These use our custom model classes instead of raw JSON
    // Jackson will automatically map the nested JSON structure
    // onto these objects for us — no manual parsing needed
    private PokemonSprites sprites;

    // List in Java is like IEnumerable or Array in C#
    // It represents the array of type objects from PokéAPI
    private List<PokemonType> types;
    private List<PokemonStat> stats;
}

// without Lombok my model would look like this
// public class Pokemon {
//     private int id;
//     private String name;

//     public int getId() { return id; }
//     public void setId(int id) { this.id = id; }
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
// }
