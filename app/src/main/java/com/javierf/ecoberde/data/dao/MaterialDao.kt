package com.javierf.ecoberde.data.dao

import androidx.room.*
import com.javierf.ecoberde.data.entities.Material

@Dao
interface MaterialDao {

    // agregar material
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarMaterial(material: Material): Long

    // actualizar material
    @Update
    suspend fun actualizarMaterial(material: Material)

    // eliminar material
    @Delete
    suspend fun eliminarMaterial(material: Material)

    // buscar materiales por nombre (parcial)
    @Query("SELECT * FROM materiales WHERE nombre LIKE '%' || :nombre || '%'")
    suspend fun buscarMaterial(nombre: String): List<Material>

    // obtener lista completa
    @Query("SELECT * FROM materiales")
    suspend fun obtenerTodos(): List<Material>

    // obtener por ID
    @Query("SELECT * FROM materiales WHERE idMaterial = :id")
    suspend fun obtenerPorId(id: Long): Material?
}
