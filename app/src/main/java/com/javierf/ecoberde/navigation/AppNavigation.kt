package com.javierf.ecoberde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.javierf.ecoberde.ui.screens.*

/** Rutas centralizadas para evitar typos */
object Routes {
    const val Login = "login"
    const val Home = "home"
    const val Clasificacion = "clasificacion"
    const val Buscar = "buscarMaterial"
    const val Agregar = "agregarMaterial"
    const val Actualizar = "actualizarMaterial"
    const val Reciclados = "materialesReciclados"
    const val Recoleccion = "recoleccion"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {
        // 🟢 LOGIN
        composable(Routes.Login) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.Home) },
                onRegister = { /* no-op */ }
            )
        }

        // 🏠 HOME
        composable(Routes.Home) {
            HomeScreen(navController = navController)
        }

        // ♻️ CLASIFICACIÓN
        composable(Routes.Clasificacion) {
            ClasificacionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { navController.navigate(Routes.Buscar) },
                onGoAgregar = { navController.navigate(Routes.Agregar) },
                onGoActualizar = { navController.navigate(Routes.Actualizar) },
                onGoReciclados = { navController.navigate(Routes.Reciclados) }
            )
        }

        // 🔍 BUSCAR MATERIAL
        composable(Routes.Buscar) {
            BuscarMaterialScreen(onBack = { navController.popBackStack() })
        }

        // ➕ AGREGAR MATERIAL
        composable(Routes.Agregar) {
            AgregarMaterialScreen(onBack = { navController.popBackStack() })
        }

        // 🔄 ACTUALIZAR MATERIAL
        composable(Routes.Actualizar) {
            ActualizarMaterialScreen(onBack = { navController.popBackStack() })
        }

        // 📦 MATERIALES RECICLADOS
        composable(Routes.Reciclados) {
            MaterialesRecicladosScreen(onBack = { navController.popBackStack() })
        }

        // 🚚 PUNTOS RECOLECCIÓN
        composable(Routes.Recoleccion) {
            RecoleccionScreen(onBack = { navController.popBackStack() })
        }
    }
}

