package com.example.examenradaroom.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.examenradaroom.crud.vehiculo.crudVehiculo
import com.example.examenradaroom.database.Vehiculos
import com.example.examenradaroom.database.configuracion
import com.example.examenradaroom.database.vehiculoDao
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

@Composable
fun listaVehiculos(navController: NavHostController, vehiculoDao: vehiculoDao, context: Context) {
    val numVehiculos = configuracion.numVeh
    var vehiculoActual = 1
    val vehiculos:List<Vehiculos> = runBlocking {
        vehiculoDao.getAllVehiculos()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row (verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "Lista vehículos", fontSize = 40.sp)
        }
        LazyColumn {
            items(1){
                while (vehiculoActual <= numVehiculos){
                    var vehiculo = vehiculos[vehiculoActual-1]
                    val velocidad = velocidadRandom()
                    Column(
                        modifier = Modifier
                            .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        if(configuracion.limite >= velocidad){
                            Text(text = "Vehiculo: $vehiculoActual")
                            Text(text = "Velocidad: $velocidad")
                        } else {
                            Text(text = "Vehiculo: $vehiculoActual")
                            Text(text = "Matricula: ${vehiculo.matricula}")
                            Text(text = "Velocidad: $velocidad")
                            Toast.makeText(context, "Matricula: ${vehiculo.matricula}, velocidad: $velocidad", Toast.LENGTH_SHORT).show()
                        }
                        vehiculoActual++
                    }
                }
            }
        }
        Button(onClick = {crudVehiculo.createVehiculo(vehiculoDao)}) {
            Text(text = "Pasa el tiempo")
        }
    }
}

fun velocidadRandom():Double{
    var velocidad = Random.nextDouble((configuracion.limite/2).toDouble(), (configuracion.limite*1.5))
    return velocidad
}

