package com.javierf.ecoberde.model

class Usuario(
    val id_usuario: Int,
    val nombre_usuario: String,
    val correo_electronico: String
) {

    fun listar_conceptos(): List<Informacion_Conceptos> {
        return Informacion_Conceptos.listar_conceptos()
    }

    fun cargar_concepto(id: Int): Informacion_Conceptos? {
        return Informacion_Conceptos.cargar_concepto(id)
    }
}
