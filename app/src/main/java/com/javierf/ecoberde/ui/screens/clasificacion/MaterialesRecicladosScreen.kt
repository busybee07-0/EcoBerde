package com.javierf.ecoberde.ui.screens.clasificacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javierf.ecoberde.data.entities.MaterialReciclado
import com.javierf.ecoberde.viewmodel.*

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.shape.RoundedCornerShape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialesRecicladosScreen(
    onBack: () -> Unit = {}
) {

    val green = Color(0xFF2E7D32)

    val context = LocalContext.current
    val factory = AppViewModelFactory(context)

    // viewmodels
    val recicladoVM: MaterialRecicladoViewModel = viewModel(factory = factory)
    val materialVM: MaterialViewModel = viewModel(factory = factory)

    // cargar historial al entrar
    LaunchedEffect(Unit) {
        recicladoVM.cargarHistorial()
        materialVM.cargarMateriales()
    }

    val listaMateriales by materialVM.materiales.collectAsState()
    val historial by recicladoVM.historial.collectAsState()

    // form states
    var fecha by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_DATE)) }
    var cantidad by remember { mutableStateOf("") }
    var materialNombre by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Materiales Reciclados") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "volver")
                    }
                }
            )
        }
    ) { inner ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(10.dp))

            /** FECHA */
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            /** SELECT MATERIAL */
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = materialNombre,
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Material") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listaMateriales.forEach { m ->
                        DropdownMenuItem(
                            text = { Text(m.nombre) },
                            onClick = {
                                materialNombre = m.nombre
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            /** CANTIDAD */
            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            /** BOTÓN GUARDAR */
            Button(
                onClick = {
                    val materialSeleccionado = listaMateriales.find { it.nombre == materialNombre }

                    if (materialSeleccionado != null && cantidad.isNotBlank()) {

                        val registro = MaterialReciclado(
                            idRegistro = 0,
                            idMaterial = materialSeleccionado.idMaterial,
                            cantidad = cantidad.toDouble(),
                            fecha = fecha
                        )

                        recicladoVM.registrar(registro) {
                            // limpiar
                            cantidad = ""
                            materialNombre = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = green)
            ) {
                Text("Registrar", color = Color.White)
            }

            Spacer(Modifier.height(25.dp))


            /** ---------- TABLA DE HISTORIAL ---------- */
            Text(
                "Historial",
                fontWeight = FontWeight.Bold,
                color = green
            )
            Spacer(Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(historial) { reg ->

                    val mat = listaMateriales.find { it.idMaterial == reg.idMaterial }
                    val nombreMat = mat?.nombre ?: "Desconocido"

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Fecha: ${reg.fecha}")
                                Text("Material: $nombreMat")
                                Text("Cantidad: ${reg.cantidad}")
                            }
                        }
                    }
                }
            }
        }
    }
}


