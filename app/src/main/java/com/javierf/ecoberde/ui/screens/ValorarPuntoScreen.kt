package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
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
fun ValorarPuntoScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    var zona by remember { mutableStateOf("Engativa") }          // placeholder visual
    var comentarios by remember { mutableStateOf("") }

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
                text = "Valora Punto",
                fontSize = 24.sp,
                color = green,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(Modifier.height(16.dp))

            // Buscador de zona / barrio (solo UI)
            OutlinedTextField(
                value = zona,
                onValueChange = { zona = it },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* sin lógica */ }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Buscar")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Tarjeta con dirección y check
            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "• Calle 60 # 9 - 25",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Seleccionar",
                        tint = green
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Estrellas (solo visual)
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    // 4 llenas y 1 vacía como en el mock (solo diseño)
                    val icon = if (index < 4) Icons.Outlined.Star else Icons.Outlined.StarBorder
                    Icon(icon, contentDescription = null, tint = Color.Black, modifier = Modifier.size(28.dp))
                }
            }

            Spacer(Modifier.height(16.dp))

            // Comentarios
            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text("Comentarios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
        }
    }
}
