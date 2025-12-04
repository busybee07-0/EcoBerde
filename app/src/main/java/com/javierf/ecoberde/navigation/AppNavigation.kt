package com.javierf.ecoberde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController

import com.javierf.ecoberde.ui.screens.*
import com.javierf.ecoberde.ui.info.*
import com.javierf.ecoberde.ui.screens.clasificacion.*

object Routes {
    const val Login = "login"
    const val Home = "home"

    const val Clasificacion = "clasificacion"
    const val BuscarMaterial = "buscarMaterial"
    const val AgregarMaterial = "agregarMaterial"
    const val ActualizarMaterial = "actualizarMaterial"
    const val Reciclados = "reciclados"

    const val InfoRRR = "infoRRR"
    const val InfoReciclaje = "infoReciclaje"
    const val GuiaMateriales = "guiaMateriales"
    const val ImportanciaReciclar = "importanciaReciclar"
    const val ImpactoMedioambiental = "impactoMedioambiental"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {

        composable(Routes.Login) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.Home) },
                onRegister = {}
            )
        }

        composable(Routes.Home) {
            HomeScreen(navController)
        }

        composable(Routes.Clasificacion) {
            ClasificacionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { navController.navigate(Routes.BuscarMaterial) },
                onGoAgregar = { navController.navigate(Routes.AgregarMaterial) },
                onGoActualizar = { navController.navigate(Routes.ActualizarMaterial) },
                onGoReciclados = { navController.navigate(Routes.Reciclados) }
            )
        }

        composable(Routes.BuscarMaterial) {
            BuscarMaterialScreen(
                onBack = { navController.popBackStack() },
                onMaterialClick = { id ->
                    navController.navigate("${Routes.ActualizarMaterial}/$id")
                }
            )
        }

        composable(Routes.AgregarMaterial) {
            AgregarMaterialScreen(
                idMaterial = null,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.AgregarMaterial}/{idMaterial}",
            arguments = listOf(navArgument("idMaterial") { type = NavType.LongType })
        ) { back ->
            val idMaterial = back.arguments?.getLong("idMaterial")
            AgregarMaterialScreen(
                idMaterial = idMaterial,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(Routes.ActualizarMaterial) {
            ActualizarMaterialScreen(
                onBack = { navController.popBackStack() },
                onMaterialClick = { id ->
                    navController.navigate("${Routes.ActualizarMaterial}/$id")
                }
            )
        }

        composable(
            route = "${Routes.ActualizarMaterial}/{idMaterial}",
            arguments = listOf(navArgument("idMaterial") { type = NavType.LongType })
        ) { back ->
            val idMaterial = back.arguments?.getLong("idMaterial") ?: 0L

            DetalleMaterialScreen(
                idMaterial = idMaterial,
                onBack = { navController.popBackStack() },
                onEdit = { id ->
                    navController.navigate("${Routes.AgregarMaterial}/$id")
                },
                onDeleteDone = { navController.popBackStack() }
            )
        }

        composable(Routes.Reciclados) {
            MaterialesRecicladosScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.InfoRRR) {
            InfoRRRScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.InfoReciclaje) {
            InfoReciclajeScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.GuiaMateriales) {
            InfoGuiaMaterialesScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.ImportanciaReciclar) {
            ImportanciaReciclarScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.ImpactoMedioambiental) {
            ImpactoMedioambienteInfoScreen(onBack = { navController.popBackStack() })
        }
    }
}


