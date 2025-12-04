package com.javierf.ecoberde.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javierf.ecoberde.R
import com.javierf.ecoberde.ui.info.components.InfoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportanciaReciclarScreen(onBack: () -> Unit = {}) {

    val green = Color(0xFF2E7D32)
    val greenLight = Color(0xFFE8F5E9)

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
                .padding(horizontal = 18.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(12.dp))

            /** Imagen principal */
            Image(
                painter = painterResource(id = R.drawable.banner_importancia),
                contentDescription = "Ilustración sobre importancia del reciclaje",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(40.dp))

            /** Título */
            Text(
                text = "¿Por qué es importante reciclar?",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(18.dp))


            // ⭐ Tarjetas principales
            InfoCard(
                icon = "🌍",
                text = "Reciclar reduce la contaminación del aire, agua y suelo.",
                background = greenLight
            )

            InfoCard(
                icon = "🌱",
                text = "Disminuye la extracción de materias primas y protege ecosistemas.",
                background = greenLight
            )

            InfoCard(
                icon = "🔥",
                text = "Evita que los residuos terminen en rellenos sanitarios o sean quemados.",
                background = greenLight
            )


            // ⭐ Tarjetas tipo C — datos interesantes
            InfoCard(
                icon = "📊",
                text = "Reciclar papel reduce la tala de árboles y consume 60% menos energía.",
                background = greenLight
            )

            InfoCard(
                icon = "⚡",
                text = "Reciclar aluminio ahorra energía suficiente para encender una casa durante un día.",
                background = greenLight
            )

            InfoCard(
                icon = "💧",
                text = "Reciclar plástico reduce la contaminación de los océanos y protege la vida marina.",
                background = greenLight
            )

            Spacer(Modifier.height(55.dp))
        }
    }
}
