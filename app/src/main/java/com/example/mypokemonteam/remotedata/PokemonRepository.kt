package com.example.mypokemonteam.remotedata

class PokemonRepository {
    private val pokemonApi: PokemonApiService = PokemonApi.createApi()

    fun getPokemon(pokemonName: String) =pokemonApi.getPokemon(pokemonName)
}