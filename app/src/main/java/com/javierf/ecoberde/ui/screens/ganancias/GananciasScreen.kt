package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.javierf.ecoberde.ui.viewmodel.GananciasViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// --------- FORMATEO FECHA (Fix del día anterior) ----------
fun Long.toLocalDateString(): String {
    val localDate = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GananciasScreen(
    viewModel: GananciasViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBack: () -> Unit = {},
    onCalcular: () -> Unit = {},
    onDetalle: () -> Unit = {},
    onHistorial: () -> Unit = {},
    onAgregarMateriales: () -> Unit = {}
) {
    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFC8E6C9)

    val datePickerState = rememberDatePickerState()

    val scrollState = rememberScrollState()

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
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
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

            // ---------- CALENDARIO ----------
            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                title = null,
                headline = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            val selectedDate = datePickerState.selectedDateMillis

            if (selectedDate != null) {
                val fecha = selectedDate.toLocalDateString()

                // Guardar fecha en ViewModel
                LaunchedEffect(fecha) {
                    viewModel.seleccionarFecha(fecha)
                }

                Text(
                    text = "Fecha seleccionada: $fecha",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(8.dp))
            }

            // ---------- BOTONES ----------
            ActionButton("Calcular Ganancias", onCalcular, greenLight)
            ActionButton("Detalle Ganancias", onDetalle, greenLight)
            ActionButton("Historial Ganancias", onHistorial, greenLight)
            ActionButton("Agregar Materiales", onAgregarMateriales, greenLight)
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

