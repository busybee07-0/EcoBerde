package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabla donde voy a guardar cada registro de material asociado a una fecha
@Entity(tableName = "ganancias")
data class GananciaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    // Fecha en formato dd/MM/yyyy (la misma que muestras en la UI)
    val fecha: String,

    // Nombre que el usuario escribió (ej: "Botellas de gaseosa")
    val nombre: String,

    // Tipo principal (Plástico, Cartón, Vidrio, etc.)
    val tipo: String,

    // Unidad que pusimos automática (kg, L, etc.)
    val unidad: String,

    // Cantidad que el usuario digita
    val cantidad: Double,

    // Precio por unidad que el usuario digita
    val precioUnidad: Double,

    // Total calculado = cantidad * precioUnidad
    val total: Double
)
