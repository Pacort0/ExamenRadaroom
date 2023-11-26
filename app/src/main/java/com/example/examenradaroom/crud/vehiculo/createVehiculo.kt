package com.example.examenradaroom.crud.vehiculo

import com.example.examenradaroom.database.Vehiculos
import com.example.examenradaroom.database.configuracion
import com.example.examenradaroom.database.vehiculoDao
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class crudVehiculo{
    companion object { //clase estática
        fun createVehiculo(vehiculoDao: vehiculoDao) {
            val nuevoVehiculo = Vehiculos(matricula = getRandomMatricula(), numMultas = 1,
                sumaMultas = configuracion.multa, velocidad = velocidadRandom())
            runBlocking { vehiculoDao.insertVehiculo(nuevoVehiculo) }
        }
    }
}
fun getRandomMatricula():Int{
    val matricula = Random.nextInt(100, 999)
    return matricula
}

fun velocidadRandom():Double{
    var velocidad = Random.nextDouble((configuracion.limite/2).toDouble(), (configuracion.limite*1.5))
    return velocidad
}