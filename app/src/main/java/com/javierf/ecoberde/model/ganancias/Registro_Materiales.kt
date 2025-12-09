package com.javierf.ecoberde.data.model.ganancias

// Lista temporal de materiales para la fecha seleccionada
class Registro_Materiales {

    private val lista = mutableListOf<Material_Ganancia>()

    fun agregarMaterial(m: Material_Ganancia) {
        lista.add(m)
    }

    fun obtenerMateriales(): List<Material_Ganancia> = lista.toList()

    fun limpiar() {
        lista.clear()
    }
}
