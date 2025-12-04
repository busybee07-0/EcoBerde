package com.javierf.ecoberde.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Recycling
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.javierf.ecoberde.navigation.Routes
import com.javierf.ecoberde.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val green = Color(0xFF2E7D32)
    val greenDark = Color(0xFF1B5E20)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                greenDark = greenDark,

                // Aquí corregimos las rutas del menú:
                onNavigateToClasificacion = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(Routes.Clasificacion)
                    }
                },
                onNavigateToRecoleccion = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(Routes.Recoleccion)
                    }
                },
                onNavigateToGanancias = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(Routes.Ganancias)
                    }
                },
                onNavigateToImpacto = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(Routes.Impacto)
                    }
                }
            )
        }
    ) {

        Scaffold(
            containerColor = Color.White,
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menú", tint = greenDark)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Página Principal",
                    fontSize = 28.sp,
                    color = green,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(Modifier.height(20.dp))

                HomeCardWithImage(
                    title = "¿Qué es RRR?",
                    image = R.drawable.banner_rrr,
                    onClick = { navController.navigate(Routes.InfoRRR) }
                )

                HomeCardWithImage(
                    title = "¿Qué es el reciclaje?",
                    image = R.drawable.banner_reciclaje,
                    onClick = { navController.navigate(Routes.InfoReciclaje) }
                )

                HomeCardWithImage(
                    title = "Guía materiales reciclables",
                    image = R.drawable.banner_guia,
                    onClick = { navController.navigate(Routes.GuiaMateriales) }
                )

                HomeCardWithImage(
                    title = "¿Por qué es importante reciclar?",
                    image = R.drawable.banner_importancia,
                    onClick = { navController.navigate(Routes.ImportanciaReciclar) }
                )

                HomeCardWithImage(
                    title = "Impacto positivo medioambiente",
                    image = R.drawable.banner_impacto,
                    onClick = { navController.navigate(Routes.ImpactoMedioambiental) }
                )
            }
        }
    }
}

@Composable
fun HomeCardWithImage(title: String, image: Int, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(16.dp))

            Text(
                text = title,
                color = Color(0xFF1B5E20),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun DrawerSheet(
    greenDark: Color,
    onNavigateToClasificacion: () -> Unit,
    onNavigateToRecoleccion: () -> Unit,
    onNavigateToGanancias: () -> Unit,
    onNavigateToImpacto: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.width(260.dp),
        drawerContainerColor = Color.White
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
            icon = { Icon(Icons.Outlined.Recycling, contentDescription = null, tint = greenDark) }
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToRecoleccion,
            label = { Text("Recolección") },
            icon = { Icon(Icons.Outlined.Delete, contentDescription = null, tint = greenDark) }
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToGanancias,
            label = { Text("Ganancias") },
            icon = { Icon(Icons.Outlined.Money, contentDescription = null, tint = greenDark) }
        )

        NavigationDrawerItem(
            selected = false,
            onClick = onNavigateToImpacto,
            label = { Text("Impacto") },
            icon = { Icon(Icons.Outlined.Eco, contentDescription = null, tint = greenDark) }
        )
    }
}
