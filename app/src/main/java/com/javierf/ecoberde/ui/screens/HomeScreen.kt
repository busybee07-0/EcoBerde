package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Recycling
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Colores verdes estilo EcoBerde
    val greenDark = Color(0xFF1B5E20)
    val green = Color(0xFF2E7D32)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(greenDark = greenDark)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /* vacío para mantener el estilo del mockup */ },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Página Principal",
                    fontSize = 28.sp,
                    color = green,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.height(16.dp))

                // Lista de “enlaces” (solo UI; no navegamos aún)
                HomeLink("¿Qué es RRR?", greenDark)
                HomeLink("¿Qué es el reciclaje?", greenDark)
                HomeLink("Guía materiales reciclables", greenDark)
                HomeLink("¿Por qué es importante reciclar?", greenDark)
                HomeLink("Impacto positivo medioambiente", greenDark)
            }
        }
    }
}

@Composable
private fun DrawerSheet(greenDark: Color) {
    ModalDrawerSheet(
        modifier = Modifier.width(220.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        // Íconos verticales (simulan el mockup). No tienen acción aún.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Icon(Icons.Outlined.Recycling, contentDescription = "Reciclaje", tint = greenDark, modifier = Modifier.size(32.dp))
            Icon(Icons.Outlined.Delete, contentDescription = "Basura", tint = greenDark, modifier = Modifier.size(32.dp))
            Icon(Icons.Outlined.VolunteerActivism, contentDescription = "Voluntariado", tint = greenDark, modifier = Modifier.size(32.dp))
            Icon(Icons.Outlined.Eco, contentDescription = "Eco", tint = greenDark, modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
private fun HomeLink(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(enabled = false) { /* sin navegación por ahora */ }
    )
}

