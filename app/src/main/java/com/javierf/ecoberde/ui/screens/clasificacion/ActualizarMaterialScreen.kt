package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarMaterialScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    val updateGreen = Color(0xFFA5D6A7)
    val deleteRed = Color(0xFFEF9A9A)

    var query by remember { mutableStateOf("Lata Cerveza") } // placeholder visual

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
                text = "Actualiza Material",
                fontSize = 24.sp,
                color = green
            )

            Spacer(Modifier.height(16.dp))

            // Barra de búsqueda (sin lógica)
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

            // Tarjeta/resultado + acciones (actualizar / eliminar)
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Imagen/placeholder clic
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Outlined.TouchApp,
                                contentDescription = "Tocar",
                                tint = Color.Gray
                            )
                            Spacer(Modifier.height(6.dp))
                            Text("Imagen / tarjeta del material", color = Color.Gray)
                        }
                    }

                    // Botones de acción (sin lógica)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { /* no-op */ },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = updateGreen)
                        ) {
                            Icon(Icons.Outlined.Update, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Actualizar", color = Color.Black)
                        }
                        Button(
                            onClick = { /* no-op */ },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = deleteRed)
                        ) {
                            Icon(Icons.Outlined.Delete, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Eliminar", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}

