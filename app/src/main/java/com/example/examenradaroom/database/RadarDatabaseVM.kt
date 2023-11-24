package com.example.examenradaroom.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room

class RadarDatabaseVM(application: Application): ViewModel() {
    val room:RadarDatabase by lazy{
        Room.databaseBuilder(application, RadarDatabase::class.java, "radardb").build()
    }
}