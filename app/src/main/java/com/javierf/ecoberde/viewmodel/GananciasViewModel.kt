package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material_ganancia")
data class MaterialGananciaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fecha: String,
    val nombre: String,
    val tipo: String,
    val unidad: String,
    val cantidad: Double,
    val precioUnidad: Double,
    val subtotal: Double
)
