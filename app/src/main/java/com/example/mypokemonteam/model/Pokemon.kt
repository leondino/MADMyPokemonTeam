package com.example.mypokemonteam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity(tableName = "pokemonTable")
data class Pokemon (

	@ColumnInfo(name = "abilities")
	@SerializedName("abilities") val abilities : List<Abilities>,
	@ColumnInfo(name = "base_experience")
	@SerializedName("base_experience") val base_experience : Int,
	@ColumnInfo(name = "forms")
	@SerializedName("forms") val forms : List<Forms>,
	@ColumnInfo(name = "game_indices")
	@SerializedName("game_indices") val game_indices : List<Game_indices>,
	@ColumnInfo(name = "height")
	@SerializedName("height") val height : Int,
	@ColumnInfo(name = "held_items")
	@SerializedName("held_items") val held_items : List<String>,
	@PrimaryKey
	@ColumnInfo(name = "id")
	@SerializedName("id") val id : Int,
	@ColumnInfo(name = "is_default")
	@SerializedName("is_default") val is_default : Boolean,
	@ColumnInfo(name = "location_area_encounters")
	@SerializedName("location_area_encounters") val location_area_encounters : String,
	@ColumnInfo(name = "moves")
	@SerializedName("moves") val moves : List<Moves>,
	@ColumnInfo(name = "name")
	@SerializedName("name") val name : String,
	@ColumnInfo(name = "order")
	@SerializedName("order") val order : Int,
	@ColumnInfo(name = "species")
	@SerializedName("species") val species : Species,
	@ColumnInfo(name = "sprites")
	@SerializedName("sprites") val sprites : Sprites,
	@ColumnInfo(name = "stats")
	@SerializedName("stats") val stats : List<Stats>,
	@ColumnInfo(name = "types")
	@SerializedName("types") val types : List<Types>,
	@ColumnInfo(name = "weight")
	@SerializedName("weight") val weight : Int,
	var nickname: String? = null
)