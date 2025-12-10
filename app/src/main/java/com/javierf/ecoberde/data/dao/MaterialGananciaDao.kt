package com.javierf.ecoberde.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javierf.ecoberde.data.entities.MaterialGananciaEntity

@Dao
interface MaterialGananciaDao {

    @Insert
    suspend fun insert(material: MaterialGananciaEntity)

    @Query("SELECT * FROM material_ganancia WHERE fecha = :fecha")
    suspend fun getMaterialesPorFecha(fecha: String): List<MaterialGananciaEntity>

    @Query("DELETE FROM material_ganancia WHERE fecha = :fecha")
    suspend fun eliminarMaterialesDeFecha(fecha: String)
}
