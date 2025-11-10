package com.javierf.ecoberde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.javierf.ecoberde.navigation.AppNavigation
import com.javierf.ecoberde.ui.theme.EcoBerdeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoBerdeTheme {
                AppNavigation() // <- Aquí conectamos todo
            }
        }
    }
}
