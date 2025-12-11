package com.javierf.ecoberde.ui.screens.ganancias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.javierf.ecoberde.ui.viewmodel.GananciasViewModel
import com.javierf.ecoberde.data.model.ganancias.Material_Ganancia
import com.javierf.ecoberde.data.database.EcoBerdeDatabase
import com.javierf.ecoberde.data.repository.GananciaRepository
import com.javierf.ecoberde.data.entities.GananciaEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroMaterialesScreen(
    viewModel: GananciasViewModel,
    fechaSeleccionada: String,
    onBack: () -> Unit = {},
    onMaterialGuardado: () -> Unit = {}
) {
    val scroll = rememberScrollState()
    val green = Color(0xFF2E7D32)

    // Contexto y corrutina para BD
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Creo la BD y el repo una sola vez en este composable
    val db = remember { EcoBerdeDatabase.getDatabase(context) }
    val gananciaDao = remember { db.gananciaDao() }
    val gananciaRepo = remember { GananciaRepository(gananciaDao) }

    // Estados del formulario
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var unidad by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    // Lista de tipos con su unidad automática
    val tipos = listOf(
        "Plástico" to "kg",
        "Cartón" to "kg",
        "Vidrio" to "kg",
        "Papel" to "kg",
        "Metal" to "kg",
        "Aceite" to "L"
    )

    var expanded by remember { mutableStateOf(false) }

    // Para mostrar errores sencillos
    var errorTexto by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Material") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { inner ->

        Column(
            modifier = Modifier
                .padding(inner)
                .padding(20.dp)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Fecha seleccionada: $fechaSeleccionada",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(20.dp))

            // CAMPO NOMBRE
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del material") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // DROPDOWN DE TIPO
            OutlinedTextField(
                value = tipo,
                onValueChange = {},
                label = { Text("Tipo de material") },
                modifier = Modifier.fillMaxWidth(),
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
                tipos.forEach { (nombreTipo, unidadTipo) ->
                    DropdownMenuItem(
                        text = { Text(nombreTipo) },
                        onClick = {
                            tipo = nombreTipo
                            unidad = unidadTipo    // se rellena solo
                            expanded = false
                        }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // UNIDAD (solo lectura)
            OutlinedTextField(
                value = unidad,
                onValueChange = {},
                readOnly = true,
                label = { Text("Unidad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // CANTIDAD
            OutlinedTextField(
                value = cantidad,
                onValueChange = { texto ->
                    cantidad = texto.filter { c -> c.isDigit() || c == '.' }
                },
                label = { Text("Cantidad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // PRECIO POR UNIDAD
            OutlinedTextField(
                value = precio,
                onValueChange = { texto ->
                    precio = texto.filter { c -> c.isDigit() || c == '.' }
                },
                label = { Text("Precio por unidad ($)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // Mensaje de error simple
            if (errorTexto != null) {
                Text(
                    text = errorTexto!!,
                    color = Color.Red,
                    fontSize = 13.sp
                )
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    // Validaciones básicas
                    if (fechaSeleccionada.isBlank()) {
                        errorTexto = "Primero seleccione una fecha en la pantalla Ganancias."
                        return@Button
                    }
                    if (nombre.isBlank() || tipo.isBlank() || unidad.isBlank()) {
                        errorTexto = "Complete nombre, tipo y unidad."
                        return@Button
                    }

                    val cantNum = cantidad.toDoubleOrNull()
                    val precioNum = precio.toDoubleOrNull()

                    if (cantNum == null || precioNum == null) {
                        errorTexto = "Cantidad y precio deben ser numéricos."
                        return@Button
                    }

                    errorTexto = null

                    // Objeto del modelo (por si lo sigues usando en el ViewModel)
                    val material = Material_Ganancia(
                        nombre = nombre,
                        tipo = tipo,
                        unidad = unidad,
                        cantidad = cantNum,
                        precioUnidad = precioNum
                    )

                    // Lo paso al ViewModel (aunque ahora también guardamos en BD)
                    viewModel.agregarMaterial(material)

                    // Entidad para Room
                    val entidad = GananciaEntity(
                        fecha = fechaSeleccionada,
                        nombre = nombre,
                        tipo = tipo,
                        unidad = unidad,
                        cantidad = cantNum,
                        precioUnidad = precioNum,
                        total = cantNum * precioNum
                    )

                    // Guardo en BD en corrutina
                    scope.launch {
                        gananciaRepo.insertarGanancia(entidad)
                        // Aquí puedo resetear formulario si quiero
                        nombre = ""
                        tipo = ""
                        unidad = ""
                        cantidad = ""
                        precio = ""
                        onMaterialGuardado()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = green
                )
            ) {
                Text("Guardar Material", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
