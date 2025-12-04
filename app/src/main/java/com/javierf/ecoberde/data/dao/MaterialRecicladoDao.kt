package com.javierf.ecoberde.data.dao

import androidx.room.*
import com.javierf.ecoberde.data.entities.MaterialReciclado

@Dao
interface MaterialRecicladoDao {

    // registrar reciclaje
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarReciclaje(registro: MaterialReciclado): Long

    // obtener registro por fecha exacta
    @Query("SELECT * FROM materiales_reciclados WHERE fecha = :fecha")
    suspend fun obtenerPorFecha(fecha: String): List<MaterialReciclado>

    // obtener historial completo
    @Query("SELECT * FROM materiales_reciclados ORDER BY idRegistro DESC")
    suspend fun obtenerHistorial(): List<MaterialReciclado>
}
