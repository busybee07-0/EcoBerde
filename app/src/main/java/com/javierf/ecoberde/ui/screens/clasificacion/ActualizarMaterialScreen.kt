package com.javierf.ecoberde.ui.screens.clasificacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.viewmodel.AppViewModelFactory
import com.javierf.ecoberde.viewmodel.MaterialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarMaterialScreen(
    onBack: () -> Unit = {},
    onMaterialClick: (Long) -> Unit = {}   // al tocar un material abre DETALLE
) {

    val context = LocalContext.current
    val factory = AppViewModelFactory(context)

    val vm: MaterialViewModel = viewModel(factory = factory)

    val materiales by vm.materiales.collectAsState()

    // cargar BD al entrar
    LaunchedEffect(Unit) { vm.cargarMateriales() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Actualizar Material") },
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
                .padding(inner)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            if (materiales.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay materiales registrados")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(materiales) { material ->
                        MaterialRowItem(
                            material = material,
                            onClick = { onMaterialClick(material.idMaterial) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MaterialRowItem(material: Material, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (material.fotoUri != null) {
                    AsyncImage(
                        model = material.fotoUri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Sin foto", color = Color.Gray)
                }
            }

            Spacer(Modifier.width(18.dp))

            Text(
                text = material.nombre,
                color = Color(0xFF1B5E20),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


