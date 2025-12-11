package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javierf.ecoberde.ui.viewmodel.GananciasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialGananciasScreen(
    viewModel: GananciasViewModel,
    onBack: () -> Unit = {}
) {
    // Cargamos/actualizamos historial al entrar a esta pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarHistorial()
    }

    val historial = viewModel.historialLista   // List<Pair<String, Double>>

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de ganancias") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Historial por día",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            if (historial.isEmpty()) {
                Text(
                    text = "Todavía no hay días con ganancias registradas.",
                    fontSize = 14.sp
                )
            } else {
                LazyColumn {
                    items(historial) { (fecha, total) ->
                        ListItem(
                            headlineContent = {
                                Text(fecha, fontWeight = FontWeight.SemiBold)
                            },
                            supportingContent = {
                                Text("Ganancia total: $ ${total.toInt()}")
                            }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

