package com.javierf.ecoberde.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javierf.ecoberde.data.entities.GananciaTotalEntity

@Dao
interface GananciaTotalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarTotal(ganancia: GananciaTotalEntity)

    @Query("SELECT * FROM ganancia_total")
    suspend fun obtenerHistorial(): List<GananciaTotalEntity>

    @Query("SELECT total FROM ganancia_total WHERE fecha = :fecha LIMIT 1")
    suspend fun obtenerTotalDia(fecha: String): Double?
}
