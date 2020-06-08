package com.example.mypokemonteam.ui

import com.example.mypokemonteam.model.Pokemon
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(private val pokemon: List<Pokemon>) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pokemon: Pokemon){

        }
    }
}