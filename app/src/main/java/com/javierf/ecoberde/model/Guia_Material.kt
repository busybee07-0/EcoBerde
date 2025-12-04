package com.javierf.ecoberde.model


data class Guia_Material(
    val id_material: Int,
    val nombre_material: String,
    val categoria: String,
    val descripcion: String
) {
    companion object {
        private val materiales = listOf(
            Guia_Material(1, "Plástico", "Reciclable", "Botellas, tapas, envases PET."),
            Guia_Material(2, "Vidrio", "Reciclable", "Botellas y frascos limpios."),
            Guia_Material(3, "Cartón", "Reciclable", "Cajas secas y limpias.")
        )

        fun cargar_material(id: Int): Guia_Material? {
            return materiales.find { it.id_material == id }
        }

        fun detalles_material(id: Int): String {
            return materiales.find { it.id_material == id }?.descripcion
                ?: "Material no encontrado"
        }
    }
}
