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
fun DetalleGananciasScreen(
    viewModel: GananciasViewModel,
    fecha: String,
    onBack: () -> Unit = {}
) {
    // Al entrar, calculamos las ganancias de ESA fecha
    LaunchedEffect(fecha) {
        viewModel.calcularGanancias(fecha)
    }

    val detalles = viewModel.detallesDia

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de ganancias") },
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
                text = if (fecha.isBlank())
                    "Materiales del día (sin fecha seleccionada)"
                else
                    "Materiales del día $fecha",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            if (detalles.isEmpty()) {
                Text(
                    text = "No hay materiales registrados para este día.",
                    fontSize = 14.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(detalles) { det ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = det.tipoMaterial,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Cantidad: ${det.cantidad}",
                                    fontSize = 13.sp
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "Subtotal: $ ${det.valorParcial.toInt()}",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

