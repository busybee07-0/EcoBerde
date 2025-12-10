package com.javierf.ecoberde.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javierf.ecoberde.data.entities.GananciaEntity

@Dao
interface GananciaDao {

    // Para guardar un registro de ganancia (un material en una fecha)
    @Insert
    suspend fun insertGanancia(ganancia: GananciaEntity)

    // Para ver todos los materiales registrados en una fecha específica
    @Query("SELECT * FROM ganancias WHERE fecha = :fecha ORDER BY id DESC")
    suspend fun getGananciasPorFecha(fecha: String): List<GananciaEntity>

    // Para ver todo el historial de registros (luego lo usamos para la pantalla de historial)
    @Query("SELECT * FROM ganancias ORDER BY fecha DESC, id DESC")
    suspend fun getTodasGanancias(): List<GananciaEntity>

    // Por si en algún momento quiero limpiar todo
    @Query("DELETE FROM ganancias")
    suspend fun borrarTodo()
}
