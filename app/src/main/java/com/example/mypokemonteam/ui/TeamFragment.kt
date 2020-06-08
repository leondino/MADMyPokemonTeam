package com.example.mypokemonteam.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent

import com.example.mypokemonteam.R
import com.example.mypokemonteam.model.Pokemon

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private val pokemon = arrayListOf<Pokemon>()
    private val pokemonAdapter = PokemonAdapter(pokemon, requireContext())
    {pokemonNumber -> onPokemonClick(pokemonNumber)}

    private val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onPokemonClick(pokemonNumber: Int) {
        // Open Serebii page with pokemons pokedex number.
        customTabsIntent.launchUrl(requireContext(),
            Uri.parse(getString(R.string.serebii_url, pokemonNumber)))
    }


}
