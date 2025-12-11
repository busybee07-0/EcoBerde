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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GananciasScreen(
    viewModel: GananciasViewModel,
    onBack: () -> Unit = {},
    onCalcular: () -> Unit = {},
    onDetalle: () -> Unit = {},
    onHistorial: () -> Unit = {},
    onAgregarMateriales: (String) -> Unit = {}   // ← CAMBIA ESTO
)
 {
    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFC8E6C9)

    val scrollState = rememberScrollState()

    // Estado del DatePicker (calendario)
    val datePickerState = rememberDatePickerState()

    // Texto amigable de la fecha seleccionada, ya ajustado a la zona horaria local
    val fechaSeleccionadaTexto by remember {
        derivedStateOf {
            val millis = datePickerState.selectedDateMillis
            if (millis != null) convertirFechaCorrecta(millis) else ""
        }
    }

    // Sincronizar fecha del calendario con el ViewModel
    LaunchedEffect(fechaSeleccionadaTexto) {
        if (fechaSeleccionadaTexto.isNotBlank()) {
            viewModel.seleccionarFecha(fechaSeleccionadaTexto)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ganancias") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
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
                .verticalScroll(scrollState),  // para que siempre veas todos los botones
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Módulo de Ganancias",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(16.dp))

            // 📅 Calendario
            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                title = null,
                headline = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            // Texto con la fecha seleccionada
            if (fechaSeleccionadaTexto.isNotBlank()) {
                Text(
                    text = "El día seleccionado es: $fechaSeleccionadaTexto",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            } else {
                Text(
                    text = "Seleccione un día en el calendario",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(16.dp))

            // 4 BOTONES
            GananciasActionButton(
                text = "Calcular Ganancias",
                onClick = onCalcular,
                background = greenLight
            )

            GananciasActionButton(
                text = "Detalle de Ganancias",
                onClick = onDetalle,
                background = greenLight
            )

            GananciasActionButton(
                text = "Historial de Ganancias",
                onClick = onHistorial,
                background = greenLight
            )

            GananciasActionButton(
                text = "Registrar Material",
                onClick = {
                    val fecha = viewModel.fechaSeleccionada  // o tu variable de texto de fecha
                    if (fecha.isNotBlank()) {
                        onAgregarMateriales(fecha)
                    }
                },
                background = greenLight
            )


            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun GananciasActionButton(
    text: String,
    onClick: () -> Unit,
    background: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = background)
    ) {
        Text(text, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

// Corrige el desfase de un día del DatePicker
private fun convertirFechaCorrecta(millis: Long): String {
    val date = java.util.Date(millis)
    val calendar = java.util.Calendar.getInstance()
    calendar.time = date

    // Ajuste: si el DatePicker da un día antes, sumamos 1
    calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)

    val dia = calendar.get(java.util.Calendar.DAY_OF_MONTH)
    val mes = calendar.get(java.util.Calendar.MONTH) + 1
    val año = calendar.get(java.util.Calendar.YEAR)

    return "%02d/%02d/%04d".format(dia, mes, año)
}

