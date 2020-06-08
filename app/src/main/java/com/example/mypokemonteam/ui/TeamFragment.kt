package com.example.mypokemonteam.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
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

    private val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonAdapter(pokemon, requireContext(),
            onClick = {pokemonNumber -> onPokemonClick(pokemonNumber)},
            onLongClick = { pokemon -> onPokemonLongClick(pokemon)})

        initViews()
    }

    fun initViews(){
        rvPokemons.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.VERTICAL, false)
        rvPokemons.adapter = pokemonAdapter
        createItemTouchHelper().attachToRecyclerView(rvPokemons)
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)

        //viewModel.pokemons.observe(requireActivity(), Observer{
        //        games ->
        //    this.pokemon.clear()
        //    this.pokemon.addAll(games)
        //    pokemonAdapter.notifyDataSetChanged()
        //})
        viewModel.latestPokemon.observe(requireActivity(), Observer{
                    latestPokemon ->
                this.pokemon.add(latestPokemon)
                pokemonAdapter.notifyDataSetChanged()
            })
    }

    private fun onPokemonClick(pokemonNumber: Int) {
        // Open Serebii page with pokemons pokedex number.
        customTabsIntent.launchUrl(requireContext(),
            Uri.parse(getString(R.string.serebii_url, pokemonNumber)))
    }

    private fun onPokemonLongClick(pokemon: Pokemon) {
        // Delete the pokemon if long clicked
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position =  viewHolder.adapterPosition
                val gameToDelete = pokemon[position]
                //viewModel.deleteGame(gameToDelete)
            }

        }
        return ItemTouchHelper(callback)
    }

}
