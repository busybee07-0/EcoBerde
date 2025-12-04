package com.javierf.ecoberde.data.entities

// Esta clase representa un material tal como lo voy a guardar en Firestore.
// Firestore necesita que todos los campos tengan valores por defecto para poder reconstruir el objeto.

data class Material(

    // Este será el ID del documento en Firestore.
    // Ya no lo genero yo: Firestore me lo da cuando agrego un material nuevo.
    val idMaterial: String? = null,

    // Nombre del material (ej: "Botella PET")
    val nombre: String = "",

    // Tipo general (plástico, vidrio, orgánico, etc.)
    val tipo: String = "",

    // Categoría (reciclable, no reciclable, peligroso, etc.)
    val categoria: String = "",

    // Descripción corta para mostrar en detalles
    val descripcion: String = "",

    // Punto donde normalmente se recicla (ej: "Punto Verde Ubaté")
    val puntoReciclaje: String = "",

    // Aquí voy a guardar la URL de la imagen que subo a Firebase Storage.
    // Así la imagen no se pierde si reinstalo la app o cambio de celular.
    val fotoUri: String? = null
) {

    // Esta función la uso para validar antes de guardar.
    // Me aseguro de que no se envíen datos vacíos a Firebase.
    fun validarDatos(): Boolean {
        return nombre.isNotBlank() && tipo.isNotBlank() && categoria.isNotBlank()
    }
}
