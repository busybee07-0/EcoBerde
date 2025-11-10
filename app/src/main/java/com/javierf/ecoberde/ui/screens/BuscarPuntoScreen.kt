package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarPuntoScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    var query by remember { mutableStateOf("Engativá") } // placeholder visual

    val items = listOf(
        "Calle 60 # 9 - 25",
        "Calle 18 # 95 - 33",
        "Carrera 7 # 135 - 20",
        "Carrera 78 # 40 - 12",
        "Avenida Suba # 110 - 45"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Busca Punto",
                fontSize = 24.sp,
                color = green,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(Modifier.height(16.dp))

            // Campo de búsqueda (sin lógica)
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* no-op */ }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Buscar")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Lista enmarcada tipo tarjeta
            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    items(items) { dir ->
                        Text(
                            text = dir,
                            color = Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                                .clickable(enabled = false) { /* solo UI */ }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}
