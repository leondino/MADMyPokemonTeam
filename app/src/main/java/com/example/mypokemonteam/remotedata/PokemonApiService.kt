package com.example.mypokemonteam.remotedata

import com.example.mypokemonteam.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface PokemonApiService {
    // Retrieves a pokemon from the API based on it's name
    @GET("pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemonName: String): Call<Pokemon>
}