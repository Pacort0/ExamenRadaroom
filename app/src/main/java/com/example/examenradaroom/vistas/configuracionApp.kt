package com.example.examenradaroom.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.examenradaroom.database.configuracion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun configuracionApp(navController: NavHostController, context: Context) {
    var limiteVelocidad by remember { mutableStateOf("") }
    var multa by remember{ mutableStateOf("") }
    var numVehiculos by remember{ mutableStateOf("") }
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row (verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "Configuracion", fontSize = 50.sp)
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            TextField(value = limiteVelocidad, onValueChange = {limiteVelocidad = it}, label = { Text(text = "Velocidad límite") })
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            TextField(value = multa, onValueChange = {multa = it}, label = { Text(text = "Precio de la multa") })
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            TextField(value = numVehiculos, onValueChange = {numVehiculos = it}, label = { Text(text = "Número de vehículos") })
        }
        Button(onClick = { guardarConfiguracion(
            limiteVelocidad = limiteVelocidad,
            multa = multa,
            numVehiculos = numVehiculos
        )}) {
            Text(text = "Guardar")
        }
        Button(onClick = {if (!campoVacio(limiteVelocidad, multa, numVehiculos)){
                navController.navigate("listaVehiculos")
            } else {
                Toast.makeText(context, "Los campos deben estar rellenos", Toast.LENGTH_SHORT).show()
        }
            }) {
           Text(text = "Lista de vehículos")
        }
    }
}

fun guardarConfiguracion(limiteVelocidad:String, multa:String, numVehiculos:String){
    configuracion.limite = limiteVelocidad.toFloat()
    configuracion.multa = multa.toDouble()
    configuracion.numVeh = numVehiculos.toInt()
}

fun campoVacio(limiteVelocidad:String, multa:String, numVehiculos:String):Boolean{
    return limiteVelocidad.isNullOrEmpty() && multa.isNullOrEmpty() && numVehiculos.isNullOrEmpty()
}