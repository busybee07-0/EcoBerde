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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.viewmodel.AppViewModelFactory
import com.javierf.ecoberde.viewmodel.MaterialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarMaterialScreen(
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

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> fotoUri = uri }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Material") },
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

            Spacer(Modifier.height(12.dp))

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
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                )
            ) {
                Text("Seleccionar Imagen", color = Color.White)
            }

            Spacer(Modifier.height(18.dp))

            // Nombre
            FormField(value = nombre, label = "Nombre") { nombre = it }

            // ---------- SELECT TIPO ----------
            val tipos = listOf("Plástico", "Vidrio", "Orgánico", "Metal", "Papel/Cartón", "Tetra Pak", "Textil", "Electrónicos")
            var expandedTipo by remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = tipo,
                    onValueChange = { },
                    label = { Text("Tipo") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedTipo = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
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

            Spacer(Modifier.height(12.dp))

            // ---------- SELECT CATEGORIA ----------
            val categorias = listOf("Reciclable", "No reciclable", "Peligroso", "Reutilizable")
            var expandedCategoria by remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = categoria,
                    onValueChange = { },
                    label = { Text("Categoría") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedCategoria = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
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

            Spacer(Modifier.height(12.dp))

            FormField(value = descripcion, label = "Descripción") { descripcion = it }
            FormField(value = punto, label = "Punto de Reciclaje") { punto = it }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val material = Material(
                        nombre = nombre,
                        tipo = tipo,
                        categoria = categoria,
                        descripcion = descripcion,
                        puntoReciclaje = punto,
                        fotoUri = fotoUri?.toString()
                    )

                    materialVM.agregar(material) {
                        onSaved()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B5E20)
                )
            ) {
                Text("Guardar", color = Color.White)
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
private fun FormField(value: String, label: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5),
            unfocusedContainerColor = Color(0xFFF5F5F5),
            disabledContainerColor = Color(0xFFE0E0E0)
        )
    )
}
