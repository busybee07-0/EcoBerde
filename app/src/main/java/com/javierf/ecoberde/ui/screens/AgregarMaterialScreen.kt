package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun AgregarMaterialScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFC8E6C9)

    var material by remember { mutableStateOf("") }
    var clasificacion by remember { mutableStateOf("") }
    var condiciones by remember { mutableStateOf("") }

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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Agrega Material",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(24.dp))

            // Campos de texto
            OutlinedTextField(
                value = material,
                onValueChange = { material = it },
                label = { Text("Material") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = clasificacion,
                onValueChange = { clasificacion = it },
                label = { Text("Clasificación") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = condiciones,
                onValueChange = { condiciones = it },
                label = { Text("Condiciones") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(32.dp))

            // Botón “Agregar” (sin acción)
            Button(
                onClick = { /* sin lógica */ },
                colors = ButtonDefaults.buttonColors(containerColor = greenLight)
            ) {
                Text(
                    "AGREGAR",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
