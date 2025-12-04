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
fun InfoGuiaMaterialesScreen(onBack: () -> Unit = {}) {

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
                painter = painterResource(id = R.drawable.banner_guia),
                contentDescription = "Guía de materiales reciclables",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(40.dp))

            /** Título */
            Text(
                text = "Guía de Materiales Reciclables",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(18.dp))


            // ⭐ Tarjetas estilo infografía
            InfoCard(
                icon = "🧴",
                text = "Plásticos PET (botellas, envases) se reciclan si están limpios y secos.",
                background = greenLight
            )

            InfoCard(
                icon = "📰",
                text = "El papel y cartón deben estar limpios, secos y sin comida adherida.",
                background = greenLight
            )

            InfoCard(
                icon = "🍾",
                text = "El vidrio es 100% reciclable y puede reprocesarse infinitas veces.",
                background = greenLight
            )

            InfoCard(
                icon = "🥫",
                text = "Las latas de aluminio y metal son altamente reciclables.",
                background = greenLight
            )

            InfoCard(
                icon = "📦",
                text = "Cajas de cartón se reciclan si no tienen grasa o suciedad.",
                background = greenLight
            )


            // ⭐ Tarjetas con mini-datos tipo C
            InfoCard(
                icon = "📊",
                text = "El 75% del aluminio producido en la historia aún sigue en uso gracias al reciclaje.",
                background = greenLight
            )

            InfoCard(
                icon = "⚡",
                text = "El plástico reciclado consume 70% menos energía que producir plástico nuevo.",
                background = greenLight
            )

            Spacer(Modifier.height(55.dp))
        }
    }
}
