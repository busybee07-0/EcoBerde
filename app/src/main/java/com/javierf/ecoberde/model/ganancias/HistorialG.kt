package com.javierf.ecoberde.data.model.ganancias

// Guarda pares fecha → total
class HistorialG {

    private val historial = mutableListOf<Pair<String, Double>>()

    fun agregarRegistro(fecha: String, total: Double) {
        historial.add(fecha to total)
    }

    fun obtenerHistorial(): List<Pair<String, Double>> = historial.toList()
}
