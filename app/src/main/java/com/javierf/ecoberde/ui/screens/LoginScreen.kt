package com.javierf.ecoberde.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Títulos
        Text(
            text = "ECOBERDE",
            fontSize = 32.sp,
            color = Color(0xFF2E7D32),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Inicio Sesión",
            fontSize = 20.sp,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(24.dp))

        // Campos
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(Modifier.height(12.dp))

        // Texto registro
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Si no estás registrado, ")
            TextButton(onClick = onRegister) {
                Text("Regístrate.", color = Color(0xFF1B5E20), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Icono reciclaje (no necesitas drawable)
        Icon(
            imageVector = Icons.Filled.Recycling,
            contentDescription = "Reciclaje",
            modifier = Modifier.size(80.dp),
            tint = Color(0xFF2E7D32)
        )

        Spacer(Modifier.height(16.dp))

        // Botón Inicio (sin lógica, solo navega)
        Button(
            onClick = onLogin,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8E6C9))
        ) {
            Text("Inicio", color = Color.Black)
        }
    }
}
