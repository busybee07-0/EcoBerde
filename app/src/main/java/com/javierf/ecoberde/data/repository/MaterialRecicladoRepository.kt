package com.javierf.ecoberde.data.repository

import com.javierf.ecoberde.data.dao.MaterialRecicladoDao
import com.javierf.ecoberde.data.entities.MaterialReciclado

class MaterialRecicladoRepository(private val recicladoDao: MaterialRecicladoDao) {


    suspend fun registrarReciclaje(registro: MaterialReciclado): Long {
        return recicladoDao.registrarReciclaje(registro)
    }


    suspend fun obtenerPorFecha(fecha: String): List<MaterialReciclado> {
        return recicladoDao.obtenerPorFecha(fecha)
    }


    suspend fun obtenerHistorial(): List<MaterialReciclado> {
        return recicladoDao.obtenerHistorial()
    }
}
