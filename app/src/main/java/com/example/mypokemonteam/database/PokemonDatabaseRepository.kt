package com.example.mypokemonteam.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mypokemonteam.model.Pokemon

class PokemonDatabaseRepository(context: Context) {
    private var pokemonDao: PokemonDao

    init {
        val pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(context)
        pokemonDao = pokemonRoomDatabase!!.pokemonDao()
    }

    fun getPokemonTeam(): LiveData<List<Pokemon>> {
        return pokemonDao.getPokemonTeam()
    }

    suspend fun insertPokemon(pokemon: Pokemon){
        return pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemon(pokemon: Pokemon){
        return pokemonDao.deletePokemon(pokemon)
    }

    suspend fun deleteAllPokemon(){
        return pokemonDao.deleteAllPokemon()
    }
}