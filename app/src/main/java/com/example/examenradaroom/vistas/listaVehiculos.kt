package com.example.examenradaroom.vistas

import android.content.Context
import android.util.Log
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
    var vehiculoActual = 0
    val vehiculos: List<Vehiculos> = runBlocking {
        vehiculoDao.getAllVehiculos()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Lista vehículos", fontSize = 40.sp)
        }
        if (vehiculos.isEmpty()) {
            Button(onClick = {
                crudVehiculo.createVehiculo(vehiculoDao)
                navController.navigate("listaVehiculos")
            }) {
                Text(text = "Pasa el tiempo")
            }
        } else {
            LazyColumn {
                items(1) {
                    while (vehiculoActual < vehiculos.size && vehiculoActual<configuracion.numVeh){
                        var vehiculo = vehiculos[vehiculoActual]
                        Column {
                            Text(text = "Vehiculo: ${vehiculoActual + 1}")
                            if (vehiculo.velocidad > configuracion.limite){
                                Text(text = "Matrícula: ${vehiculo.matricula}")
                                Text(text = "Velocidad: ${vehiculo.velocidad}")
                            } else {
                                Text(text = "Velocidad: ${vehiculo.velocidad}")
                            }
                            vehiculoActual++
                        }
                    }
                }
            }
            Button(onClick = {
                if (vehiculoActual < configuracion.numVeh) {
                    crudVehiculo.createVehiculo(vehiculoDao)
                    navController.navigate("listaVehiculos")
                    Log.i("prueba", "He entrado en el if, vehiculo actual: $vehiculoActual, Numero de vehículos: ${configuracion.numVeh}")
                } else {
                    Toast.makeText(context, "No van a pasar más coches", Toast.LENGTH_SHORT).show()
                    Log.i("prueba", "He entrado en el else, vehiculo actual: $vehiculoActual, Numero de vehículos: ${configuracion.numVeh}")
                }
            }) {
                Text(text = "Pasa el tiempo")
            }
        }
    }
}



