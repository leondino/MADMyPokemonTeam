package com.example.mypokemonteam.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mypokemonteam.R

class SplashActivity : AppCompatActivity() {

    private var splashScreenLengthSeconds: Long = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // Start the PokemonActivity (Main Activity with Fragments) after given seconds.
            startActivity(
                Intent(
                    this@SplashActivity,
                    PokemonActivity::class.java
                )
            )
            finish()
        }, splashScreenLengthSeconds * 1000)
    }
}
