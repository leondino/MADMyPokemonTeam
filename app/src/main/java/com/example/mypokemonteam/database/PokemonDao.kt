package com.example.mypokemonteam.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mypokemonteam.model.DataPokemon
import com.example.mypokemonteam.model.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemonTable")
    fun getPokemonTeam(): LiveData<List<DataPokemon>>

    @Insert
    suspend fun insertPokemon(pokemon: DataPokemon)

    @Delete
    suspend fun deletePokemon(pokemon: DataPokemon)

    @Query("DELETE FROM pokemonTable")
    suspend fun deleteAllPokemon()
}