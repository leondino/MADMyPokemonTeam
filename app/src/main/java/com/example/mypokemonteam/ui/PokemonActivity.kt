package com.example.mypokemonteam.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.mypokemonteam.R

import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.content_pokemon.*

class PokemonActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
        initNavigation()
    }

    private fun initViews(){

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        viewModel.pokemonsData.observe(this, Observer {
            // Get data from api based on database pokemon data
            pokemon ->
            viewModel.latestPokemonList.clear()
            for(dataPokemon in pokemon)
                viewModel.getPokemon(dataPokemon.pokemonName, dataPokemon.nickname)
            viewModel.pokemons.value = viewModel.latestPokemonList
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_delete, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.deleteAll-> {
                //deleteGames()
                Toast.makeText(this, viewModel.pokemons.value?.get(0)?.name, Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
