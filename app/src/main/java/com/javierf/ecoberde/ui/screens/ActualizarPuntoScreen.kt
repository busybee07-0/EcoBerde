package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Update
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
fun ActualizarPuntoScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    val updateGreen = Color(0xFFA5D6A7)  // verde claro botón actualizar
    val deleteRed = Color(0xFFEF9A9A)    // rojo claro botón eliminar

    var query by remember { mutableStateOf("Engativá") }

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
                text = "Actualiza Punto",
                fontSize = 24.sp,
                color = green,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(Modifier.height(16.dp))

            // Buscador
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* sin lógica */ }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Buscar")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Tarjeta con dirección + botones actualizar / eliminar (mock)
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = "• Calle 60 # 9 - 25",
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { /* sin lógica */ },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = updateGreen)
                        ) {
                            Icon(Icons.Outlined.Update, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Actualizar", color = Color.Black)
                        }
                        Button(
                            onClick = { /* sin lógica */ },
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
