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
fun GananciasScreen(
    onBack: () -> Unit = {},
    onCalcular: () -> Unit = {},
    onDetalle: () -> Unit = {},
    onHistorial: () -> Unit = {},
    onAgregarMateriales: () -> Unit = {}
) {
    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFC8E6C9)

    // Estado del DatePicker
    val datePickerState = rememberDatePickerState()  // selecciona hoy por defecto

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
                text = "Ganancias",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(16.dp))

            // 📅 Calendario real (inline)
            DatePicker(
                state = datePickerState,
                showModeToggle = true,        // botón para cambiar a vista mes/año
                title = null,                  // como en tu mock
                headline = null,               // evita header grande
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // (Opcional) fecha seleccionada — solo visual
            val selectedDateMillis = datePickerState.selectedDateMillis
            if (selectedDateMillis != null) {
                Text(
                    text = "Fecha seleccionada: ${java.text.SimpleDateFormat("dd MMM yyyy").format(java.util.Date(selectedDateMillis))}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(8.dp))
            }

            // Botones solicitados (sin lógica)
            ActionButton("Calcular Ganancias", onCalcular, greenLight)
            ActionButton("Detalle Ganancias", onDetalle, greenLight)
            ActionButton("Historial Ganancias", onHistorial, greenLight)
            ActionButton("Agrega Materiales", onAgregarMateriales, greenLight)
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
    bg: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bg)
    ) {
        Text(text, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}
