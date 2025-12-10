package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MaterialDetalleDemo(
    val nombre: String,
    val tipo: String,
    val cantidad: String,
    val unidad: String,
    val subtotal: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleGananciasScreen(
    onBack: () -> Unit = {}
) {
    val demoLista = listOf(
        MaterialDetalleDemo("Botellas PET", "Plástico", "5.0", "kg", "$ 15.000"),
        MaterialDetalleDemo("Cartón corrugado", "Cartón", "3.0", "kg", "$ 9.000"),
        MaterialDetalleDemo("Vidrio verde", "Vidrio", "4.0", "kg", "$ 8.000"),
        MaterialDetalleDemo("Papel blanco", "Papel", "2.5", "kg", "$ 5.000"),
        MaterialDetalleDemo("Latas de aluminio", "Metal", "1.5", "kg", "$ 8.000")
    )

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
                text = "Materiales del día 09/12/2025",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(demoLista) { material ->
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
                                text = material.nombre,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "${material.tipo} • ${material.cantidad} ${material.unidad}",
                                fontSize = 13.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Subtotal: ${material.subtotal}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

