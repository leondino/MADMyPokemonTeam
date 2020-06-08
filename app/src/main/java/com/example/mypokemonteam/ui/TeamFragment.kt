package com.example.mypokemonteam.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mypokemonteam.R
import com.example.mypokemonteam.model.Pokemon

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private val pokemon = arrayListOf<Pokemon>()
    private val pokemonAdapter = PokemonAdapter(pokemon, requireContext())
    {pokemonNumber -> onPokemonClick(pokemonNumber)}

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

    private fun onPokemonClick(pokemonNumber: String) {

    }


}
