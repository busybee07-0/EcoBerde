package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javierf.ecoberde.model.ganancias.Material_Ganancia
import com.javierf.ecoberde.viewmodel.GananciasViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroMaterialesScreen(
    onBack: () -> Unit,
    onSaved: () -> Unit = {},
    viewModel: GananciasViewModel = viewModel()
) {
    val green = Color(0xFF2E7D32)
    val red = Color(0xFFD32F2F)

    // estados de los campos
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var unidad by remember { mutableStateOf("") }
    var cantidadTexto by remember { mutableStateOf("") }
    var precioTexto by remember { mutableStateOf("") }

    var expandedTipo by remember { mutableStateOf(false) }

    val tipos = listOf(
        "Plástico",
        "Vidrio",
        "Papel / Cartón",
        "Orgánico",
        "Metal",
        "Otro"
    )

    // fecha que viene del ViewModel
    val fechaSeleccionada = viewModel.fechaSeleccionada

    // formatito para el precio
    val numberFormat = remember {
        NumberFormat.getInstance(Locale("es", "CO")).apply {
            maximumFractionDigits = 0
            isGroupingUsed = true // para 1.000, 20.000, etc
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar material") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // ---------------- FECHA ----------------
            if (fechaSeleccionada.isBlank()) {
                Text(
                    text = "Primero seleccione una fecha en la pantalla Ganancias.",
                    color = red,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Fecha seleccionada: $fechaSeleccionada",
                    color = green,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            // --------------- NOMBRE -----------------
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del material") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // --------------- TIPO (DROPDOWN) --------
            ExposedDropdownMenuBox(
                expanded = expandedTipo,
                onExpandedChange = { expandedTipo = !expandedTipo }
            ) {
                OutlinedTextField(
                    value = tipo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de material") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipo)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedTipo,
                    onDismissRequest = { expandedTipo = false }
                ) {
                    tipos.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                tipo = opcion
                                expandedTipo = false

                                // unidad auto según tipo
                                unidad = when (opcion) {
                                    "Plástico" -> "kg"
                                    "Vidrio" -> "kg"
                                    "Papel / Cartón" -> "kg"
                                    "Orgánico" -> "kg"
                                    "Metal" -> "kg"
                                    else -> "unidad"
                                }
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // --------------- UNIDAD (SOLO LECTURA) --
            OutlinedTextField(
                value = unidad,
                onValueChange = {},
                label = { Text("Unidad") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // --------------- CANTIDAD ---------------
            OutlinedTextField(
                value = cantidadTexto,
                onValueChange = { nuevo ->
                    // solo números y punto
                    cantidadTexto = nuevo.filter { it.isDigit() || it == '.' }
                },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // --------------- PRECIO POR UNIDAD -----
            OutlinedTextField(
                value = precioTexto,
                onValueChange = { nuevo ->
                    val limpio = nuevo.filter { it.isDigit() }
                    if (limpio.isBlank()) {
                        precioTexto = ""
                    } else {
                        try {
                            val numero = limpio.toLong()
                            precioTexto = numberFormat.format(numero)
                        } catch (_: NumberFormatException) {
                            // si se desborda, simplemente no actualizo
                        }
                    }
                },
                label = { Text("Precio por unidad") },
                leadingIcon = {
                    Text(text = "$", fontWeight = FontWeight.Bold)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // --------------- BOTÓN GUARDAR ----------
            Button(
                onClick = {
                    if (fechaSeleccionada.isBlank()) return@Button

                    val cantNum = cantidadTexto.toDoubleOrNull() ?: 0.0
                    val precioNum = precioTexto
                        .replace(".", "")
                        .replace(",", "")
                        .toDoubleOrNull() ?: 0.0

                    val material = Material_Ganancia(
                        nombre = nombre,
                        tipo = tipo,
                        unidad = unidad,
                        cantidad = cantNum,
                        precioUnidad = precioNum.toDouble()
                    )

                    if (material.validar()) {
                        viewModel.agregarMaterial(material)
                        // limpio campos
                        nombre = ""
                        tipo = ""
                        unidad = ""
                        cantidadTexto = ""
                        precioTexto = ""

                        onSaved()
                    }
                },
                enabled = fechaSeleccionada.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar material")
            }
        }
    }
}



