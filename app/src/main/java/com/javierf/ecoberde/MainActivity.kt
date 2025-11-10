package com.javierf.ecoberde


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.javierf.ecoberde.ui.screens.LoginScreen
import com.javierf.ecoberde.ui.theme.EcoBerdeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoBerdeTheme {
                // Mostramos directamente la pantalla de login
                LoginScreen(
                    onLogin = { /* aún no hacemos navegación */ },
                    onRegister = {}
                )
            }
        }
    }
}
