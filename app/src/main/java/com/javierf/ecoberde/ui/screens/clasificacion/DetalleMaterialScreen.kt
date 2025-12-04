package com.javierf.ecoberde.ui.screens.clasificacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.javierf.ecoberde.viewmodel.AppViewModelFactory
import com.javierf.ecoberde.viewmodel.MaterialViewModel
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleMaterialScreen(
    idMaterial: Long,
    onBack: () -> Unit = {},
    onEdit: (Long) -> Unit = {},
    onDeleteDone: () -> Unit = {}
) {

    val context = LocalContext.current
    val factory = AppViewModelFactory(context)

    // viewmodel donde están los métodos del crud
    val materialVM: MaterialViewModel = viewModel(factory = factory)

    // material actual
    var material by remember { mutableStateOf<com.javierf.ecoberde.data.entities.Material?>(null) }

    // cargo el material cuando entro
    LaunchedEffect(Unit) {
        material = materialVM.obtenerPorId(idMaterial)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Material") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "volver")
                    }
                }
            )
        }
    ) { inner ->

        material?.let { m ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(20.dp))

                // foto grande
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(Color(0xFFEFEFEF), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {

                    if (m.fotoUri != null) {
                        AsyncImage(
                            model = m.fotoUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("Sin imagen")
                    }
                }

                Spacer(Modifier.height(30.dp))

                // nombre
                Text(
                    text = m.nombre,
                    fontSize = 26.dp.value.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )

                Spacer(Modifier.height(14.dp))

                InfoLine(label = "Tipo", value = m.tipo)
                InfoLine(label = "Categoría", value = m.categoria)
                InfoLine(label = "Descripción", value = m.descripcion)
                InfoLine(label = "Punto de Reciclaje", value = m.puntoReciclaje)

                Spacer(Modifier.height(28.dp))

                // botones crud
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    // editar material
                    Button(
                        onClick = { onEdit(m.idMaterial) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32)
                        )
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Editar")
                    }

                    // eliminar material
                    Button(
                        onClick = {
                            // borrar desde el vm
                            materialVM.eliminar(m) {
                                onDeleteDone()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD32F2F)
                        )
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Eliminar")
                    }
                }
            }
        } ?: run {
            // por si el material aun no ha cargado
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun InfoLine(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
        Text(value)
        Spacer(Modifier.height(10.dp))
    }
}
