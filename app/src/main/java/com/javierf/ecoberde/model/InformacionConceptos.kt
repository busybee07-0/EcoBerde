package com.javierf.ecoberde.model

data class Concepto(
    val id: Int,
    val categoria: String,
    val titulo: String,
    val contenido: List<String>,
    val imagen: Int
)
