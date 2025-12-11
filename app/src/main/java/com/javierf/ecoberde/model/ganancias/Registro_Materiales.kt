package com.javierf.ecoberde.data.model.ganancias

// Registro de materiales separado por fecha (dd/MM/yyyy)
class Registro_Materiales {

    // Mapa: "11/12/2025" -> [material1, material2, ...]
    private val registrosPorFecha = mutableMapOf<String, MutableList<Material_Ganancia>>()

    fun agregarMaterial(fecha: String, material: Material_Ganancia) {
        val lista = registrosPorFecha.getOrPut(fecha) { mutableListOf() }
        lista.add(material)
    }

    fun obtenerMateriales(fecha: String): List<Material_Ganancia> {
        return registrosPorFecha[fecha]?.toList() ?: emptyList()
    }

    fun limpiar(fecha: String) {
        registrosPorFecha.remove(fecha)
    }
}

