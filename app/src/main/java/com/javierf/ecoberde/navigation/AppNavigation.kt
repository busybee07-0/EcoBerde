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
import com.javierf.ecoberde.ui.screens.ganancias.GananciasScreen
import com.javierf.ecoberde.ui.screens.ganancias.RegistroMaterialesScreen

/**
 * OBJETO RUTAS
 */
object Routes {
    const val Login = "login"
    const val Home = "home"

    // MENÚ PRINCIPAL
    const val Clasificacion = "clasificacion"
    const val Recoleccion = "recoleccion"
    const val Ganancias = "ganancias"
    const val Impacto = "impacto"

    // CRUD Clasificación
    const val BuscarMaterial = "buscarMaterial"
    const val AgregarMaterial = "agregarMaterial"
    const val ActualizarMaterial = "actualizarMaterial"
    const val Reciclados = "reciclados"

    // INFO (Home)
    const val InfoRRR = "infoRRR"
    const val InfoReciclaje = "infoReciclaje"
    const val GuiaMateriales = "guiaMateriales"
    const val ImportanciaReciclar = "importanciaReciclar"
    const val ImpactoMedioambiental = "impactoMedioambiental"

    // GANANCIAS SUBRUTAS
    const val RegistroMaterial = "registroMaterial"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {

        // ============================================================
        // LOGIN
        // ============================================================
        composable(Routes.Login) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.Home) },
                onRegister = {}
            )
        }

        // ============================================================
        // HOME
        // ============================================================
        composable(Routes.Home) {
            HomeScreen(navController)
        }

        // ============================================================
        // CLASIFICACIÓN — PANTALLA PRINCIPAL
        // ============================================================
        composable(Routes.Clasificacion) {
            ClasificacionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { navController.navigate(Routes.BuscarMaterial) },
                onGoAgregar = { navController.navigate(Routes.AgregarMaterial) },
                onGoActualizar = { navController.navigate(Routes.ActualizarMaterial) },
                onGoReciclados = { navController.navigate(Routes.Reciclados) }
            )
        }

        // ============================================================
        // CRUD CLASIFICACIÓN
        // ============================================================
        composable(Routes.BuscarMaterial) {
            BuscarMaterialScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.AgregarMaterial) {
            AgregarMaterialScreen(
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
        ) { backStackEntry ->
            val idMaterial = backStackEntry.arguments?.getLong("idMaterial") ?: 0L
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
            MaterialesRecicladosScreen(onBack = { navController.popBackStack() })
        }

        // ============================================================
        // RECOLECCIÓN
        // ============================================================
        composable(Routes.Recoleccion) {
            RecoleccionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { /* pendiente */ },
                onGoAgregar = { /* pendiente */ },
                onGoActualizar = { /* pendiente */ },
                onGoValorar = { /* pendiente */ }
            )
        }

        // ============================================================
        // GANANCIAS
        // ============================================================
        composable(Routes.Ganancias) {
            GananciasScreen(
                onBack = { navController.popBackStack() },
                onAgregarMateriales = { navController.navigate(Routes.RegistroMaterial) }
            )
        }

        // SUBPANTALLA: REGISTRAR MATERIALES
        composable(Routes.RegistroMaterial) {
            RegistroMaterialesScreen(
                onBack = { navController.popBackStack() },
                onMaterialGuardado = { navController.popBackStack() }
            )
        }

        // ============================================================
        // IMPACTO
        // ============================================================
        composable(Routes.Impacto) {
            ImpactoScreen(onBack = { navController.popBackStack() })
        }

        // ============================================================
        // INFO (HOME)
        // ============================================================
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




