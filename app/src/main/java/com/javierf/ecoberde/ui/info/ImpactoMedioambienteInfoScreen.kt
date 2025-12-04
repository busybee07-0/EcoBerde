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
fun ImpactoMedioambienteInfoScreen(onBack: () -> Unit = {}) {

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
                painter = painterResource(id = R.drawable.banner_impacto),
                contentDescription = "Impacto medioambiental positivo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(40.dp))

            /** Título */
            Text(
                text = "Impacto Positivo en el Medioambiente",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = green
            )

            Spacer(Modifier.height(18.dp))


            // ⭐ Tarjetas principales (Estilo A)
            InfoCard(
                icon = "🌿",
                text = "Reciclar conserva recursos naturales esenciales para los ecosistemas.",
                background = greenLight
            )

            InfoCard(
                icon = "🌬️",
                text = "Reduce emisiones que contribuyen al cambio climático.",
                background = greenLight
            )

            InfoCard(
                icon = "🌊",
                text = "Disminuye la contaminación de ríos, mares y océanos.",
                background = greenLight
            )

            InfoCard(
                icon = "🦋",
                text = "Ayuda a proteger la biodiversidad y la vida silvestre.",
                background = greenLight
            )


            // ⭐ Tarjetas estilo C
            InfoCard(
                icon = "📊",
                text = "Reciclar 1 tonelada de papel salva 17 árboles y 26.500 litros de agua.",
                background = greenLight
            )

            InfoCard(
                icon = "🔋",
                text = "Reciclar metales reduce el 95% del consumo energético industrial.",
                background = greenLight
            )

            InfoCard(
                icon = "💡",
                text = "Cada botella de vidrio reciclada ahorra energía suficiente para encender una bombilla por 4 horas.",
                background = greenLight
            )

            Spacer(Modifier.height(55.dp))
        }
    }
}
