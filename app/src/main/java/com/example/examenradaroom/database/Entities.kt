package com.example.examenradaroom.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "tVehiculos")
data class Vehiculos(
    @PrimaryKey
    val matricula:Int = 0,
    val numMultas:Int = 0,
    val sumaMultas:Double = 0.0,
    val velocidad:Double = 0.0
)

@Entity(tableName = "tSancion")
data class Sancion(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val matricula: Int,
    val velocidad:Double = 0.0
)


