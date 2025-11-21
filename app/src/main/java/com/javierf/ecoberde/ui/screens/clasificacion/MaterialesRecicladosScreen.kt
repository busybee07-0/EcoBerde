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
fun MaterialesRecicladosScreen(onBack: () -> Unit = {}) {
    val green = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFC8E6C9)

    var fecha by remember { mutableStateOf("20 Septiembre") }
    var aluminio by remember { mutableStateOf("0") }
    var plastico by remember { mutableStateOf("0") }
    var vidrio by remember { mutableStateOf("0") }

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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Materiales",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(20.dp))

            // Campo de fecha (solo UI)
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                trailingIcon = {
                    IconButton(onClick = { /* sin acción */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Selector de fecha",
                            tint = Color.Gray
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            // Campos para materiales
            MaterialRow(label = "Aluminio", value = aluminio, onChange = { aluminio = it })
            MaterialRow(label = "Plástico", value = plastico, onChange = { plastico = it })
            MaterialRow(label = "Vidrio", value = vidrio, onChange = { vidrio = it })

            Spacer(Modifier.height(28.dp))

            // Botón Guardar
            Button(
                onClick = { /* sin lógica */ },
                colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
            ) {
                Text("GUARDAR", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun MaterialRow(label: String, value: String, onChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        )
    }
}
