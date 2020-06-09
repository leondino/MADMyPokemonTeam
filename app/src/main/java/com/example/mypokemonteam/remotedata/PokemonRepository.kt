package com.example.mypokemonteam.remotedata

class PokemonRepository {
    private val pokemonApi: PokemonApiService = PokemonApi.createApi()

    // Retrieves a pokemon from the API based on it's name
    fun getPokemon(pokemonName: String) = pokemonApi.getPokemon(pokemonName)
}