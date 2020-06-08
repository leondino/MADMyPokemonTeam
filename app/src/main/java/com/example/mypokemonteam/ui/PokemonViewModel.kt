package com.example.mypokemonteam.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokemonteam.database.PokemonDatabaseRepository
import com.example.mypokemonteam.model.Pokemon
import com.example.mypokemonteam.remotedata.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel(application: Application): AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val pokemonRepository = PokemonRepository()
    private val pokemonDatabaseRepository = PokemonDatabaseRepository(application.applicationContext)
    val latestPokemon = MutableLiveData<Pokemon>()
    val error = MutableLiveData<String>()

    val pokemons: LiveData<List<Pokemon>> = pokemonDatabaseRepository.getPokemonTeam()

    fun insertPokemon(pokemon: Pokemon){
        ioScope.launch {
            pokemonDatabaseRepository.insertPokemon(pokemon)
        }
    }

    fun deletePokemon(pokemon: Pokemon){
        ioScope.launch {
            pokemonDatabaseRepository.deletePokemon(pokemon)
        }
    }

    fun deleteAllPokemon(pokemon: Pokemon){
        ioScope.launch {
            pokemonDatabaseRepository.deleteAllPokemon()
        }
    }

    fun getPokemon(pokemonName: String, nickname: String){
        pokemonRepository.getPokemon(pokemonName).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    latestPokemon.value = response.body()
                    latestPokemon.value?.nickname = nickname
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                error.value = t.message
            }
        })
    }

    fun isPokemonValid(pokemonName: String, nickname: String): Boolean{
        return !(pokemonName.isBlank() || nickname.isBlank())
    }

}