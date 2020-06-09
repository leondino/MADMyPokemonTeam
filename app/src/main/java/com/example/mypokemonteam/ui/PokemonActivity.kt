package com.example.mypokemonteam.ui

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        // Set title because of splash screen not refreshing toolbar name.
        supportActionBar?.title = getString(R.string.app_name)
        initViewModel()
        initNavigation()
        initViews()
    }

    private fun initViews(){
        // Reload fragment on start to load data received from database
        Handler().postDelayed({
            findNavController(R.id.navHostFragment).navigate(R.id.action_teamFragment_self)
        }, RELOAD_SPEED_MS)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        // Observing the pokemonsData list which updates with the database
        viewModel.pokemonsData.observe(this, Observer {
            // Get data from api based on database pokemon data.
            pokemon ->
            viewModel.latestPokemonList.clear()
            for(dataPokemon in pokemon)
                viewModel.getPokemon(dataPokemon)
            viewModel.pokemons.value = viewModel.latestPokemonList
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initNavigation() {

        val navController = findNavController(R.id.navHostFragment)

        // Connecting the navigation controller to the bottom menu.
        NavigationUI.setupWithNavController(navView, navController)

        // Connecting the navigation controller with the toolbar
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
                viewModel.deleteAllPokemon()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val RELOAD_SPEED_MS: Long = 2000
    }

}
