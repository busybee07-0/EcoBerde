package com.javierf.ecoberde.model.ganancias

// Esta clase la uso para representar cada material registrado
data class Material_Ganancia(
    val nombre: String,        // nombre que escribió el usuario
    val tipo: String,          // plástico, vidrio, etc.
    val unidad: String,        // kg, unidad, litro, etc.
    val cantidad: Double,      // cantidad numérica
    val precioUnidad: Double   // precio por unidad (lo que el usuario digitó)
) {

    // acá saco el valor total = cantidad * precioUnidad
    fun calcularValorTotal(): Double = cantidad * precioUnidad

    // validación sencilla para no guardar basura
    fun validar(): Boolean {
        return nombre.isNotBlank() &&
                tipo.isNotBlank() &&
                unidad.isNotBlank() &&
                cantidad > 0 &&
                precioUnidad > 0
    }
}

