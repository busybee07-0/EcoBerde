package com.javierf.ecoberde.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materiales")
data class Material(

    // id autogenerado
    @PrimaryKey(autoGenerate = true)
    val idMaterial: Long = 0L,

    // nombre del material
    val nombre: String = "",

    // tipo: plástico, vidrio, orgánico, etc.
    val tipo: String = "",

    // categoría: reciclable, no reciclable, peligroso, etc.
    val categoria: String = "",

    // info corta del material
    val descripcion: String = "",

    // punto donde normalmente se recicla
    val puntoReciclaje: String = "",

    // uri de la foto elegida por el usuario (guardado como texto)
    // lo dejo así porque así lo puedo cargar fácil desde la galería
    val fotoUri: String? = null
) {

    // validar datos antes de guardar
    // esto lo uso para que no se guarden campos vacíos
    fun validarDatos(): Boolean {
        return nombre.isNotBlank() && tipo.isNotBlank() && categoria.isNotBlank()
    }

    // actualizar usando un objeto que ya tenga los cambios
    // esto solo ayuda a simplificar cuando edite un material
    fun actualizarMaterial(nuevo: Material): Material {
        return this.copy(
            nombre = nuevo.nombre,
            tipo = nuevo.tipo,
            categoria = nuevo.categoria,
            descripcion = nuevo.descripcion,
            puntoReciclaje = nuevo.puntoReciclaje,
            fotoUri = nuevo.fotoUri
        )
    }
}
