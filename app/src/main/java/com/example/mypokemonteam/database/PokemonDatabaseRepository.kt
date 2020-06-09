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

    // Gets LiveData list of all data pokemon in database
    fun getPokemonTeam(): LiveData<List<DataPokemon>> {
        return pokemonDao.getPokemonTeam()
    }

    // Inserts the data pokemon in the database and updates it
    suspend fun insertPokemon(pokemon: DataPokemon){
        return pokemonDao.insertPokemon(pokemon)
    }

    // Deletes the data pokemon in the database and updates it
    suspend fun deletePokemon(pokemon: DataPokemon){
        return pokemonDao.deletePokemon(pokemon)
    }

    // Deletes all the data pokemon in the database and updates it
    suspend fun deleteAllPokemon(){
        return pokemonDao.deleteAllPokemon()
    }
}