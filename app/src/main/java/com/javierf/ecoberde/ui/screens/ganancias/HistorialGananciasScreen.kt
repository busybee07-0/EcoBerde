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

data class RegistroHistorialDemo(
    val fecha: String,
    val total: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialGananciasScreen(
    onBack: () -> Unit = {}
) {
    val historialDemo = listOf(
        RegistroHistorialDemo("09/12/2025", "$ 45.000"),
        RegistroHistorialDemo("08/12/2025", "$ 32.500"),
        RegistroHistorialDemo("07/12/2025", "$ 27.000"),
        RegistroHistorialDemo("06/12/2025", "$ 18.000")
    )

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

            LazyColumn {
                items(historialDemo) { registro ->
                    ListItem(
                        headlineContent = {
                            Text(registro.fecha, fontWeight = FontWeight.SemiBold)
                        },
                        supportingContent = {
                            Text("Ganancia total: ${registro.total}")
                        }
                    )
                    Divider()
                }
            }
        }
    }
}

