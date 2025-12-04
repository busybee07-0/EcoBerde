// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// 🔥 Repositorios necesarios para descargar Firebase, Compose y todo el proyecto.
repositories {
    google()       // ← ESTO ES OBLIGATORIO PARA FIREBASE
    mavenCentral()
}

