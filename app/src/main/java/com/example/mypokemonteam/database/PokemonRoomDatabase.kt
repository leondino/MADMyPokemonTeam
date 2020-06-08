package com.example.mypokemonteam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mypokemonteam.model.DataPokemon
import com.example.mypokemonteam.model.Pokemon

@Database(entities = [DataPokemon::class], version = 1, exportSchema = false)
abstract class PokemonRoomDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object{
        private const val DATABASE_NAME = "POKEMON_DATABASE"

        @Volatile
        private var INSTANCE: PokemonRoomDatabase? = null

        fun getDatabase(context: Context): PokemonRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(PokemonRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, PokemonRoomDatabase::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}