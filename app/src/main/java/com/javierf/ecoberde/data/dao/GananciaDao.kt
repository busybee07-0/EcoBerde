package com.javierf.ecoberde.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javierf.ecoberde.data.entities.GananciaEntity

@Dao
interface GananciaDao {

    // Inserto una sola ganancia (un registro de material en una fecha)
    @Insert
    suspend fun insertarGanancia(ganancia: GananciaEntity)

    // Todas las ganancias de un día específico
    @Query("SELECT * FROM ganancias WHERE fecha = :fecha")
    suspend fun obtenerGananciasPorFecha(fecha: String): List<GananciaEntity>

    // TODO: luego podemos hacer consultas más bonitas para historial
    @Query("SELECT * FROM ganancias")
    suspend fun obtenerTodas(): List<GananciaEntity>
}

