package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Money
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
import androidx.navigation.NavController
import com.javierf.ecoberde.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val greenDark = Color(0xFF1B5E20)
    val green = Color(0xFF2E7D32)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                greenDark = greenDark,
                onNavigateToClasificacion = {
                    scope.launch { drawerState.close(); navController.navigate(Routes.Clasificacion) }
                },
                onNavigateToRecoleccion = {
                    scope.launch { drawerState.close(); navController.navigate(Routes.Recoleccion) }
                },
                onNavigateToGanancias = {
                    scope.launch { drawerState.close(); navController.navigate(Routes.Ganancias) }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
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

                // Lista de secciones (solo diseño)
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
private fun DrawerSheet(
    greenDark: Color,
    onNavigateToClasificacion: () -> Unit,
    onNavigateToRecoleccion: () -> Unit,
    onNavigateToGanancias: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.width(260.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Menú",
            color = greenDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToClasificacion,
            label = { Text("Clasificación") },
            icon = { Icon(Icons.Outlined.Recycling, contentDescription = null, tint = greenDark) },
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToRecoleccion,
            label = { Text("Recolección") },
            icon = { Icon(Icons.Outlined.Delete, contentDescription = null, tint = greenDark) },
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToGanancias,
            label = { Text("Ganancias") },
            icon = { Icon(Icons.Outlined.Money, contentDescription = null, tint = greenDark) },
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // Extras del mock (sin navegación)
        NavigationDrawerItem(
            selected = false,
            onClick = { /* Voluntariado */ },
            label = { Text("Voluntariado") },
            icon = { Icon(Icons.Outlined.VolunteerActivism, contentDescription = null, tint = greenDark) },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        NavigationDrawerItem(
            selected = false,
            onClick = { /* Eco */ },
            label = { Text("Eco") },
            icon = { Icon(Icons.Outlined.Eco, contentDescription = null, tint = greenDark) },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
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
            .clickable(enabled = false) { /* solo diseño */ }
    )
}




