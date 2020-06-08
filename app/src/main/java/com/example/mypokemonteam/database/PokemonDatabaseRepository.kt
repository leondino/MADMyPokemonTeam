package com.example.mypokemonteam.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mypokemonteam.model.DataPokemon
import com.example.mypokemonteam.model.Pokemon

class PokemonDatabaseRepository(context: Context) {
    private var pokemonDao: PokemonDao

    init {
        val pokemonRoomDatabase = PokemonRoomDatabase.getDatabase(context)
        pokemonDao = pokemonRoomDatabase!!.pokemonDao()
    }

    fun getPokemonTeam(): LiveData<List<DataPokemon>> {
        return pokemonDao.getPokemonTeam()
    }

    suspend fun insertPokemon(pokemon: DataPokemon){
        return pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemon(pokemon: DataPokemon){
        return pokemonDao.deletePokemon(pokemon)
    }

    suspend fun deleteAllPokemon(){
        return pokemonDao.deleteAllPokemon()
    }
}