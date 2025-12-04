package com.javierf.ecoberde.ui.info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoCard(
    icon: String,
    text: String,
    background: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(background, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = icon,
                fontSize = 28.sp,
                modifier = Modifier.padding(end = 12.dp),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
