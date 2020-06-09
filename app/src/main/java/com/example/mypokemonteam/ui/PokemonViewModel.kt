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
    // API repository
    private val pokemonRepository = PokemonRepository()
    //Database repository
    private val pokemonDatabaseRepository = PokemonDatabaseRepository(application.applicationContext)
    // Reference to latest added pokemon to be added to latest pokemon list.
    val latestPokemon = MutableLiveData<Pokemon>()
    // Error message
    val error = MutableLiveData<String>()
    // List with current DataPokemon. This gets retrieved from teh database.
    val pokemonsData: LiveData<List<DataPokemon>> = pokemonDatabaseRepository.getPokemonTeam()
    // List with current pokemons
    val pokemons = MutableLiveData<List<Pokemon>>()
    // Holds latest pokemon to update LiveData pokemons list
    val latestPokemonList =  arrayListOf<Pokemon>()

    // Inserts the data pokemon in the database and updates it
    fun insertPokemon(pokemon: DataPokemon){
        ioScope.launch {
            pokemonDatabaseRepository.insertPokemon(pokemon)
        }
    }

    // Deletes the data pokemon in the database and updates it
    fun deletePokemon(pokemon: DataPokemon){
        ioScope.launch {
            pokemonDatabaseRepository.deletePokemon(pokemon)
        }
    }

    // Deletes all the data pokemon in the database and updates it
    fun deleteAllPokemon(){
        ioScope.launch {
            pokemonDatabaseRepository.deleteAllPokemon()
        }
    }

    /* Retrieves a full Pokemon object based on the data from a DataPokemon
     * and puts it in a list with list with latest retrieved pokemon.
     * This method also gives a nickname to the Pokemon and a reference to the
     * DataPokemon it was made of so it can be deleted from the database later on.
     * On Failure the DataPokemon immediately gets deleted to prevent them from being
     * stuck in the database and keep giving errors.
     */
    fun getPokemon(pokemon: DataPokemon){
        pokemonRepository.getPokemon(pokemon.pokemonName).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    latestPokemon.value = response.body()
                    latestPokemon.value?.nickname = pokemon.nickname
                    latestPokemon.value?.dataPokemon = pokemon
                    latestPokemonList.add(latestPokemon.value!!)
                }
                else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                    deletePokemon(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                error.value = t.message
                deletePokemon(pokemon)
            }
        })
    }

    // Clicking this button will update the database with the given variables
    fun isPokemonValid(pokemonName: String, nickname: String): Boolean{
        return !(pokemonName.isBlank() || nickname.isBlank())
    }

    // Check if party/team is full (max 6)
    fun isPartyFull(): Boolean{
        return (pokemons.value?.size!! >= 6)
    }

}