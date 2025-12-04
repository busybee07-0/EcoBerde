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
fun InfoRRRScreen(onBack: () -> Unit = {}) {

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

            /** Imagen superior */
            Image(
                painter = painterResource(id = R.drawable.banner_rrr),
                contentDescription = "Imagen sobre RRR",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(40.dp))

            /** Título */
            Text(
                text = "¿Qué es RRR?",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(18.dp))

            /** Tarjetas informativas */
            InfoCard(
                icon = "♻️",
                text = "RRR significa Reducir, Reutilizar y Reciclar.",
                background = greenLight
            )

            InfoCard(
                icon = "📉",
                text = "Reducir ayuda a disminuir la cantidad de residuos que generamos.",
                background = greenLight
            )

            InfoCard(
                icon = "🔁",
                text = "Reutilizar extiende la vida útil de los objetos antes de desecharlos.",
                background = greenLight
            )

            InfoCard(
                icon = "♼",
                text = "Reciclar convierte materiales usados en nuevos productos útiles.",
                background = greenLight
            )

            InfoCard(
                icon = "📊",
                text = "Aplicar RRR reduce hasta un 30% de la basura doméstica generada.",
                background = greenLight
            )

            Spacer(Modifier.height(55.dp))
        }
    }
}
