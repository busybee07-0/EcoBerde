package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClasificacionScreen(
    onBack: () -> Unit = {},
    onGoBuscar: () -> Unit = {},
    onGoAgregar: () -> Unit = {},
    onGoActualizar: () -> Unit = {},
    onGoReciclados: () -> Unit = {}
) {
    val green = Color(0xFF2E7D32)
    val greenDark = Color(0xFF1B5E20)

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
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ícono principal
            Icon(
                imageVector = Icons.Filled.Recycling,
                contentDescription = "Reciclaje",
                tint = green,
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Título
            Text(
                text = "Clasificación Residuos",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(24.dp))

            // Botones de navegación
            ClasificacionButton("Busca Material", Icons.Filled.Search, onClick = onGoBuscar)
            ClasificacionButton("Agrega Material", Icons.Filled.Add, onClick = onGoAgregar)
            ClasificacionButton("Actualiza Material", Icons.Outlined.Update, onClick = onGoActualizar)
            ClasificacionButton("Materiales Reciclados", Icons.Filled.Recycling, onClick = onGoReciclados)
        }
    }
}

@Composable
private fun ClasificacionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    val greenDark = Color(0xFF1B5E20)
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = greenDark)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, color = Color.Black, fontSize = 16.sp)
            Icon(icon, contentDescription = null, tint = greenDark)
        }
    }
}
