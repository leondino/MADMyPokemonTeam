package com.example.mypokemonteam.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypokemonteam.R
import com.example.mypokemonteam.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(private val pokemon: List<Pokemon>, private val context: Context,
                     private val onClick: (Int) -> Unit, private val onLongClick: (Pokemon) -> Unit)
: RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener{
                onClick(pokemon[adapterPosition].id)
            }
            itemView.setOnLongClickListener {
                onLongClick(pokemon[adapterPosition])
                true
            }
        }

        fun bind(pokemon: Pokemon){

            // Sets the pokemons species name and nickname as one name.
            itemView.tvPokemonName.text =
                context.getString(R.string.pokemon_name, pokemon.nickname, pokemon.name)

            // Some pokemon have one type other have 2. This code handles that and sets the right types.
            if (pokemon.types.size == 1)
                itemView.tvType.text =
                    context.getString(R.string.pokemon_type_mono, pokemon.types[0].type.name)
            else
                itemView.tvType.text =
                    context.getString(R.string.pokemon_type_duo, pokemon.types[0].type.name, pokemon.types[1].type.name)

            // Sets the pokemons pokedex number
            itemView.tvNumber.text = context.getString(R.string.pokemon_number, pokemon.id)

            // Sets the pokemons sprite as picture
            Glide.with(context).load(pokemon.sprites.front_default).into(itemView.ivPokemon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemon[position])
    }
}