package com.javierf.ecoberde.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.javierf.ecoberde.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLogin = { navController.navigate("home") },
                onRegister = { /* más adelante */ }
            )
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("clasificacion") {
            ClasificacionScreen(onBack = { navController.popBackStack() })
        }

        composable("buscarMaterial") {
            BuscarMaterialScreen(onBack = { navController.popBackStack() })
        }

        composable("clasificacion") {
            ClasificacionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { navController.navigate("buscarMaterial") }
            )
        }

        composable("agregarMaterial") {
            AgregarMaterialScreen(onBack = { navController.popBackStack() })
        }

        composable("clasificacion") {
            ClasificacionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { navController.navigate("buscarMaterial") },
                onGoAgregar = { navController.navigate("agregarMaterial") }
            )
        }





    }
}
