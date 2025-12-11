package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javierf.ecoberde.ui.viewmodel.GananciasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcularGananciasScreen(
    viewModel: GananciasViewModel,
    onBack: () -> Unit = {}
) {
    val green = MaterialTheme.colorScheme.primary

    // Pedimos al ViewModel que calcule con la fecha actual
    LaunchedEffect(Unit) {
        viewModel.calcularGanancias()
    }

    val fecha = viewModel.fechaSeleccionada
    val total = viewModel.totalDia ?: 0.0
    val detalles = viewModel.detallesDia
    val cantidadMateriales = detalles.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calcular ganancias") },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Resumen del día",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = if (fecha.isNotBlank()) "Fecha: $fecha" else "Fecha: (sin seleccionar)",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = green.copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Ganancia total del día",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "$ ${"%,.0f".format(total)}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = green
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "A partir de $cantidadMateriales materiales registrados.",
                        fontSize = 13.sp
                    )
                }
            }


        }
    }
}


