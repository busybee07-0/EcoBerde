package com.javierf.ecoberde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel

import com.javierf.ecoberde.ui.screens.*
import com.javierf.ecoberde.ui.info.*
import com.javierf.ecoberde.ui.screens.clasificacion.*
import com.javierf.ecoberde.ui.screens.ganancias.*
import com.javierf.ecoberde.ui.viewmodel.GananciasViewModel

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

    // 🟢 GANANCIAS
    const val RegistroMaterial = "registroMaterial"
    const val CalcularGanancias = "calcularGanancias"
    const val DetalleGanancias = "detalleGanancias"
    const val HistorialGanancias = "historialGanancias"

    // INFO (Home)
    const val InfoRRR = "infoRRR"
    const val InfoReciclaje = "infoReciclaje"
    const val GuiaMateriales = "guiaMateriales"
    const val ImportanciaReciclar = "importanciaReciclar"
    const val ImpactoMedioambiental = "impactoMedioambiental"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val gananciasViewModel: GananciasViewModel = viewModel()

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
        // CLASIFICACIÓN
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
            MaterialesRecicladosScreen(onBack = { navController.popBackStack() })
        }

        // ============================================================
        // RECOLECCIÓN (placeholder)
        // ============================================================
        composable(Routes.Recoleccion) {
            RecoleccionScreen(
                onBack = { navController.popBackStack() },
                onGoBuscar = { /* futuro */ },
                onGoAgregar = { /* futuro */ },
                onGoActualizar = { /* futuro */ },
                onGoValorar = { /* futuro */ }
            )
        }

        // ============================================================
        // 🟢 GANANCIAS
        // ============================================================
        composable(Routes.Ganancias) {
            GananciasScreen(
                viewModel = gananciasViewModel,   // ← ¡ESTE ES EL QUE FALTABA!
                onBack = { navController.popBackStack() },
                onCalcular = { navController.navigate(Routes.CalcularGanancias) },
                onDetalle = { navController.navigate(Routes.DetalleGanancias) },
                onHistorial = { navController.navigate(Routes.HistorialGanancias) },
                onAgregarMateriales = { fecha ->
                    val safeFecha = fecha.replace("/", "-")
                    navController.navigate("${Routes.RegistroMaterial}/$safeFecha")
                }
            )
        }


        // Registrar material – recibe la fecha por argumento
        composable(
            route = "${Routes.RegistroMaterial}/{fecha}",
            arguments = listOf(
                navArgument("fecha") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val fechaArg = backStackEntry.arguments?.getString("fecha") ?: ""
            // volvemos a poner las barras para mostrarla bonita
            val fechaMostrar = fechaArg.replace("-", "/")

            RegistroMaterialesScreen(
                viewModel = gananciasViewModel,
                fechaSeleccionada = fechaMostrar,
                onBack = { navController.popBackStack() },
                onMaterialGuardado = { navController.popBackStack() }
            )
        }

        // ▼▼▼ PANTALLAS DE GANANCIAS (mock visual) ▼▼▼
        composable(Routes.CalcularGanancias) {
            CalcularGananciasScreen(
                viewModel = gananciasViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.DetalleGanancias) {
            DetalleGananciasScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.HistorialGanancias) {
            HistorialGananciasScreen(onBack = { navController.popBackStack() })
        }
        // ▲▲▲ FIN GANANCIAS ▲▲▲

        // ============================================================
        // IMPACTO
        // ============================================================
        composable(Routes.Impacto) {
            ImpactoScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ============================================================
        // INFO HOME
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
