package com.javierf.ecoberde.data.repository

import com.javierf.ecoberde.data.dao.MaterialDao
import com.javierf.ecoberde.data.entities.Material

class MaterialRepository(private val materialDao: MaterialDao) {


    suspend fun agregarMaterial(material: Material): Long {
        return materialDao.agregarMaterial(material)
    }


    suspend fun actualizarMaterial(material: Material) {
        materialDao.actualizarMaterial(material)
    }


    suspend fun eliminarMaterial(material: Material) {
        materialDao.eliminarMaterial(material)
    }


    suspend fun buscarMaterial(nombre: String): List<Material> {
        return materialDao.buscarMaterial(nombre)
    }


    suspend fun obtenerPorId(id: Long): Material? {
        return materialDao.obtenerPorId(id)
    }


    suspend fun obtenerTodos(): List<Material> {
        return materialDao.obtenerTodos()
    }
}
