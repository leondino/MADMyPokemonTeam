package com.example.mypokemonteam.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.mypokemonteam.R

import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.content_pokemon.*

class PokemonActivity : AppCompatActivity() {

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

    }

    private fun initNavigation() {
        val navController = findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }


}
