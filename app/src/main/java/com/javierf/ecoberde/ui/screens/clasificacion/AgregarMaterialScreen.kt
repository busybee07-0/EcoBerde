package com.javierf.ecoberde.ui.screens.clasificacion

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.viewmodel.AppViewModelFactory
import com.javierf.ecoberde.viewmodel.MaterialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarMaterialScreen(
    idMaterial: Long? = null,
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {}
) {

    val context = LocalContext.current
    val factory = AppViewModelFactory(context)
    val materialVM: MaterialViewModel = viewModel(factory = factory)

    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var punto by remember { mutableStateOf("") }
    var fotoUri by remember { mutableStateOf<Uri?>(null) }

    // Cargar datos si estamos editando
    LaunchedEffect(idMaterial) {
        if (idMaterial != null) {
            val m = materialVM.obtenerPorId(idMaterial)
            if (m != null) {
                nombre = m.nombre
                tipo = m.tipo
                categoria = m.categoria
                descripcion = m.descripcion
                punto = m.puntoReciclaje
                fotoUri = m.fotoUri?.let { Uri.parse(it) }
            }
        }
    }

    // Abrir galería
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        fotoUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (idMaterial == null) "Agregar Material" else "Actualizar Material") },
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
                .padding(20.dp)
        ) {

            // Foto preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (fotoUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(fotoUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Sin foto")
                }
            }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = { pickImageLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
            ) {
                Text("Seleccionar Imagen", color = Color.White)
            }

            Spacer(Modifier.height(14.dp))


            /** ------ CAMPOS ------- */

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            // SELECT TIPO
            val tipos = listOf("Plástico", "Vidrio", "Orgánico", "Metal", "Papel/Cartón", "Tetra Pak", "Textil", "Electrónicos")
            var expandedTipo by remember { mutableStateOf(false) }

            Box(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = tipo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo") },
                    trailingIcon = {
                        IconButton(onClick = { expandedTipo = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expandedTipo,
                    onDismissRequest = { expandedTipo = false }
                ) {
                    tipos.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                tipo = opcion
                                expandedTipo = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            // SELECT CATEGORIA
            val categorias = listOf("Reciclable", "No reciclable", "Peligroso", "Reutilizable")
            var expandedCategoria by remember { mutableStateOf(false) }

            Box(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = {
                        IconButton(onClick = { expandedCategoria = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expandedCategoria,
                    onDismissRequest = { expandedCategoria = false }
                ) {
                    categorias.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                categoria = opcion
                                expandedCategoria = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = punto,
                onValueChange = { punto = it },
                label = { Text("Punto de Reciclaje") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))


            /** ------ GUARDAR ------- */

            Button(
                onClick = {
                    val material = Material(
                        idMaterial = idMaterial ?: 0L,
                        nombre = nombre,
                        tipo = tipo,
                        categoria = categoria,
                        descripcion = descripcion,
                        puntoReciclaje = punto,
                        fotoUri = fotoUri?.toString()
                    )

                    if (idMaterial == null) {
                        // NUEVO
                        materialVM.agregar(material) { onSaved() }
                    } else {
                        // ACTUALIZAR
                        materialVM.modificar(material) { onSaved() }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
            ) {
                Text(if (idMaterial == null) "Guardar" else "Actualizar", color = Color.White)
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}
