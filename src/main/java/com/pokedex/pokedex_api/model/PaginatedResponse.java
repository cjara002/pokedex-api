package com.pokedex.pokedex_api.model;

import java.util.List;

import lombok.Data;

@Data
// The <T> makes this class generic — T is a placeholder for any type
public class PaginatedResponse<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;
    
}
