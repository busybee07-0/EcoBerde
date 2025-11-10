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
fun ImpactoScreen(
    onBack: () -> Unit = {},
    onCalcular: () -> Unit = {},
    onDetalle: () -> Unit = {},
    onHistorial: () -> Unit = {},
    onAgregarMateriales: () -> Unit = {}
) {
    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFC8E6C9)

    val datePickerState = rememberDatePickerState()

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
                text = "Impacto",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(12.dp))

            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                title = null,
                headline = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            ActionButton("Calcular Impacto", onCalcular, greenLight)
            ActionButton("Detalle Impacto", onDetalle, greenLight)
            ActionButton("Historial Impacto", onHistorial, greenLight)
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
