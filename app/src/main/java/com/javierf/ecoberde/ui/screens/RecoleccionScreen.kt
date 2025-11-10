package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ThumbUp
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
fun RecoleccionScreen(
    onBack: () -> Unit = {},
    onGoBuscar: () -> Unit = {},
    onGoAgregar: () -> Unit = {},
    onGoActualizar: () -> Unit = {},
    onGoValorar: () -> Unit = {}
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
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Puntos Recolección",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(24.dp))

            RecoleccionButton("Busca Punto", Icons.Filled.Search, onClick = onGoBuscar)
            RecoleccionButton("Agrega Punto", Icons.Filled.Add, onClick = onGoAgregar)
            RecoleccionButton("Actualiza Punto", Icons.Outlined.Update, onClick = onGoActualizar)
            RecoleccionButton("Valora Punto R", Icons.Outlined.ThumbUp, onClick = onGoValorar)
        }
    }
}

@Composable
private fun RecoleccionButton(
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
