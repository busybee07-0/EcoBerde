package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tabla de ganancias por material y fecha.
 *
 * Aquí guardo cada registro que el usuario crea en "Registrar Material":
 * - fecha: la fecha seleccionada en el módulo de Ganancias (dd/MM/yyyy)
 * - nombre, tipo, unidad: vienen del formulario
 * - cantidad y precioUnidad: los uso para el cálculo
 * - total: cantidad * precioUnidad (lo puedo calcular antes de guardar)
 */
@Entity(tableName = "ganancias")
data class GananciaEntity(

    @PrimaryKey(autoGenerate = true)
    val idGanancia: Long = 0L,

    val fecha: String,          // ej: "10/12/2025"
    val nombre: String,
    val tipo: String,
    val unidad: String,         // kg, L, etc.
    val cantidad: Double,
    val precioUnidad: Double,
    val total: Double           // cantidad * precioUnidad
)

