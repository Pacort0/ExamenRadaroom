package com.example.examenradaroom

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenradaroom.database.RadarDatabaseVM
import com.example.examenradaroom.ui.theme.ExamenRadaroomTheme
import com.example.examenradaroom.vistas.configuracionApp
import com.example.examenradaroom.vistas.detallesVehiculosMultados
import com.example.examenradaroom.vistas.listaVehiculos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenRadaroomTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val database = RadarDatabaseVM(context.applicationContext as Application).room
                NavHost(navController = navController, startDestination = "configuracionApp"){
                    composable(route="configuracionApp"){
                        configuracionApp(navController)
                    }
                    composable(route = "detallesVehiculosMultados"){
                        detallesVehiculosMultados(navController, database.sancionDao(), context)
                    }
                    composable(route = "listaVehiculos"){
                        listaVehiculos(navController,database.vehiculoDao(), context)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamenRadaroomTheme {
        Greeting("Android")
    }
}