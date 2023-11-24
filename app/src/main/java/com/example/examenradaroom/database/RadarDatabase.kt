package com.example.examenradaroom.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vehiculos::class, Sancion::class], version = 1, exportSchema = false)
abstract class RadarDatabase: RoomDatabase(){
    abstract fun vehiculoDao(): vehiculoDao
    abstract fun sancionDao(): sancionDao
}