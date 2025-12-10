package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ganancia_total")
data class GananciaTotalEntity(
    @PrimaryKey val fecha: String,
    val total: Double
)

