package com.javierf.ecoberde.data.repository

import com.javierf.ecoberde.data.dao.GananciaDao
import com.javierf.ecoberde.data.entities.GananciaEntity

/**
 * Repo de ganancias.
 *
 * Yo uso esta clase como capa intermedia entre el ViewModel y Room.
 * Así no llamo directamente al DAO desde las pantallas.
 */
class GananciaRepository(
    private val gananciaDao: GananciaDao
) {

    // Guardar un registro de ganancia
    suspend fun insertarGanancia(ganancia: GananciaEntity) {
        gananciaDao.insertarGanancia(ganancia)
    }

    // Traer todas las ganancias de una fecha específica
    suspend fun obtenerGananciasPorFecha(fecha: String): List<GananciaEntity> {
        return gananciaDao.obtenerGananciasPorFecha(fecha)
    }

    // Traer todo el historial
    suspend fun obtenerTodas(): List<GananciaEntity> {
        return gananciaDao.obtenerTodas()
    }
}
