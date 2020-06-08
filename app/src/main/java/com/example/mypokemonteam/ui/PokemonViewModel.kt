package com.example.mypokemonteam.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokemonteam.database.PokemonDatabaseRepository
import com.example.mypokemonteam.model.DataPokemon
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

    val pokemonsData: LiveData<List<DataPokemon>> = pokemonDatabaseRepository.getPokemonTeam()
    val pokemons = MutableLiveData<List<Pokemon>>()
    val latestPokemonList =  arrayListOf<Pokemon>()

    fun insertPokemon(pokemon: DataPokemon){
        ioScope.launch {
            pokemonDatabaseRepository.insertPokemon(pokemon)
        }
    }

    fun deletePokemon(pokemon: DataPokemon){
        ioScope.launch {
            pokemonDatabaseRepository.deletePokemon(pokemon)
        }
    }

    fun deleteAllPokemon(){
        ioScope.launch {
            pokemonDatabaseRepository.deleteAllPokemon()
        }
    }

    fun getPokemon(pokemon: DataPokemon){
        pokemonRepository.getPokemon(pokemon.pokemonName).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    latestPokemon.value = response.body()
                    latestPokemon.value?.nickname = pokemon.nickname
                    latestPokemon.value?.dataPokemon = pokemon
                    latestPokemonList.add(latestPokemon.value!!)
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

    fun isPartyFull(): Boolean{
        return (pokemons.value?.size!! >= 6)
    }

}