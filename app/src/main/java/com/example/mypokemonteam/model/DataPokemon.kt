package com.example.mypokemonteam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonTable")
data class DataPokemon(

    @ColumnInfo(name = "pokemonName")
    var pokemonName: String,

    @ColumnInfo(name = "nickname")
    var nickname: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)
