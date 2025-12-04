package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materiales_reciclados")
data class MaterialReciclado(
    @PrimaryKey(autoGenerate = true)
    val idRegistro: Long = 0L,

    val idMaterial: Long,    // referencia a Material
    val cantidad: Double,
    val fecha: String        // luego se puede convertir a LocalDate
)

