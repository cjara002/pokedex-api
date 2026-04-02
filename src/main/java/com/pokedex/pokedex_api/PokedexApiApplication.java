package com.pokedex.pokedex_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  tells Spring to scan all your packages for components like controllers
//  and services, enables auto-configuration so Spring wires everything together
//  automatically, and marks this as a configuration class. 

// Component Scanning
// When you put @Service on a class, Spring says "that's a service, I'll manage it too." 
// No manual registration needed at all.
@SpringBootApplication
public class PokedexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApiApplication.class, args);
	}

}
