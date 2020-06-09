package com.example.mypokemonteam.ui

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mypokemonteam.R
import com.example.mypokemonteam.model.Pokemon
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private val pokemon = arrayListOf<Pokemon>()
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var viewModel: PokemonViewModel

    // Custom Chrome tabs for Serebii.net
    private val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialise adapter
        pokemonAdapter = PokemonAdapter(pokemon, requireContext(),
            onClick = {pokemonNumber -> onPokemonClick(pokemonNumber)},
            onLongClick = { pokemon -> onPokemonLongClick(pokemon)})

        initViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    fun initViews(){
        // Initialise recyclerview
        rvPokemons.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.VERTICAL, false)
        rvPokemons.adapter = pokemonAdapter
        //Handler().postDelayed({
        //    // Start the PokemonActivity (Main Activity with Fragments) after given seconds.
        //    findNavController().navigate(R.id.action_teamFragment_self)
        //}, 2000)
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)

        // Observes the pokemons list and updates the pokemon list and recyclerview of this fragment accordingly.
        viewModel.pokemons.observe(requireActivity(), Observer{
                pokemon ->
            this.pokemon.clear()
            this.pokemon.addAll(pokemon)
            pokemonAdapter.notifyDataSetChanged()
        })
    }

    private fun onPokemonClick(pokemonNumber: Int) {
        // Open Serebii.net page with pokemons pokedex number.
        customTabsIntent.launchUrl(requireContext(),
            Uri.parse(getString(R.string.serebii_url, pokemonNumber)))
    }

    private fun onPokemonLongClick(pokemon: Pokemon) {
        // Delete the pokemon if long clicked
        viewModel.deletePokemon(pokemon.dataPokemon!!)

        Handler().postDelayed({
            // Reloads the fragment to show changes
            findNavController().navigate(R.id.action_teamFragment_self)
        }, PokemonActivity.RELOAD_SPEED_MS)
    }

}
