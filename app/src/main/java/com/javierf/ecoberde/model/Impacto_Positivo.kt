package com.javierf.ecoberde.model

data class Impacto_Positivo(
    val id_impacto: Int,
    val tipo_impacto: String,
    val descripcion: String
) {

    companion object {

        private val impactos = listOf(
            Impacto_Positivo(
                1,
                "CO₂ evitado",
                "El reciclaje reduce emisiones de carbono."
            ),
            Impacto_Positivo(
                2,
                "Agua ahorrada",
                "Menos producción implica menos consumo de agua."
            ),
            Impacto_Positivo(
                3,
                "Energía conservada",
                "Reciclar usa menos energía que producir desde cero."
            )
        )

        fun cargar_impacto(id: Int): Impacto_Positivo? {
            return impactos.find { it.id_impacto == id }
        }

        fun detalles_impacto(id: Int): String {
            return impactos.find { it.id_impacto == id }?.descripcion
                ?: "Impacto no encontrado"
        }
    }
}
