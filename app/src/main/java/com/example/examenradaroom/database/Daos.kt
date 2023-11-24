package com.example.examenradaroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface vehiculoDao {
    @Query("SELECT * FROM tvehiculos")
    suspend fun getAllVehiculos():MutableList<Vehiculos>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehiculo(vehiculos: Vehiculos)
}

@Dao
interface sancionDao{
    @Query("SELECT S.matricula, S.id, S.velocidad FROM tsancion as S INNER JOIN tVehiculos AS V ON S.matricula = V.matricula")
    suspend fun getSancionDeVehiculo():Sancion

    @Insert
    suspend fun insertSancion(sancion: Sancion)
}