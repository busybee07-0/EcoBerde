package com.javierf.ecoberde.data.model.ganancias

data class Material_Ganancia(
    val nombre: String = "",
    val tipo: String = "",
    val unidad: String = "",
    val cantidad: Double = 0.0,
    val precioUnidad: Double = 0.0
) {
    fun validar(): Boolean {
        return nombre.isNotBlank() &&
                tipo.isNotBlank() &&
                unidad.isNotBlank() &&
                cantidad > 0 &&
                precioUnidad > 0
    }

    fun calcularValorTotal(): Double {
        return cantidad * precioUnidad
    }
}


