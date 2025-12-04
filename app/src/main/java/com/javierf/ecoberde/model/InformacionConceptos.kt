package com.javierf.ecoberde.model

data class Informacion_Conceptos(
    val id_concepto: Int,
    val titulo_concepto: String,
    val descripcion: String,
    val emoji: String
) {
    companion object {


        private val conceptos = listOf(


            Informacion_Conceptos(
                id_concepto = 1,
                titulo_concepto = "¿Qué es RRR?",
                emoji = "♻️",
                descripcion =
                    """
                    ♻️ RRR significa Reducir, Reutilizar y Reciclar.
                    
                    📉 Reducir ayuda a disminuir la cantidad de residuos que generamos.
                    
                    🔁 Reutilizar extiende la vida útil de los objetos antes de desecharlos.
                    
                    ♼ Reciclar convierte materiales usados en nuevos productos útiles.
                    
                    📊 Aplicar RRR reduce hasta un 30% de la basura doméstica generada.
                    """.trimIndent()
            ),


            Informacion_Conceptos(
                id_concepto = 2,
                titulo_concepto = "¿Qué es el reciclaje?",
                emoji = "♼",
                descripcion =
                    """
                    ♼ El reciclaje transforma residuos en nuevos materiales útiles.
                    
                    🧴 Se reciclan plástico, vidrio, metal, papel y cartón limpios.
                    
                    🗂 Requiere separación correcta en casa para que funcione bien.
                    
                    📊 Reciclar una lata de aluminio ahorra 95% de la energía de producir una nueva.
                    
                    ⚡ Una botella de vidrio puede reciclarse infinitas veces sin perder calidad.
                    """.trimIndent()
            ),


            Informacion_Conceptos(
                id_concepto = 3,
                titulo_concepto = "Guía de materiales reciclables",
                emoji = "🧴",
                descripcion =
                    """
                    🧴 Plásticos PET (botellas, envases) se reciclan si están limpios y secos.
                    
                    📰 Papel y cartón deben estar limpios, secos y sin comida adherida.
                    
                    🍾 El vidrio es 100% reciclable y puede reprocesarse infinitas veces.
                    
                    🥫 Las latas de aluminio y metal son altamente reciclables.
                    
                    📦 El cartón se recicla si no tiene grasa o suciedad.
                    
                    📊 El 75% del aluminio producido en la historia aún sigue en uso gracias al reciclaje.
                    
                    ⚡ El plástico reciclado consume 70% menos energía que producir plástico nuevo.
                    """.trimIndent()
            ),


            Informacion_Conceptos(
                id_concepto = 4,
                titulo_concepto = "¿Por qué es importante reciclar?",
                emoji = "🌍",
                descripcion =
                    """
                    🌍 Reciclar reduce la contaminación del aire, agua y suelo.
                    
                    🌱 Disminuye la extracción de materias primas y protege ecosistemas.
                    
                    🔥 Evita que los residuos terminen en rellenos sanitarios o sean quemados.
                    
                    📊 Reciclar papel reduce la tala de árboles y consume 60% menos energía.
                    
                    ⚡ Reciclar aluminio ahorra energía suficiente para encender una casa por un día.
                    
                    💧 Reciclar plástico reduce la contaminación de océanos y protege la vida marina.
                    """.trimIndent()
            ),


            Informacion_Conceptos(
                id_concepto = 5,
                titulo_concepto = "Impacto Positivo Medioambiente",
                emoji = "🌿",
                descripcion =
                    """
                    🌿 Reciclar conserva recursos naturales esenciales para los ecosistemas.
                    
                    🌬️ Reduce emisiones que contribuyen al cambio climático.
                    
                    🌊 Disminuye la contaminación de ríos, mares y océanos.
                    
                    🦋 Ayuda a proteger la biodiversidad y la vida silvestre.
                    
                    📊 Reciclar 1 tonelada de papel salva 17 árboles y 26.500 litros de agua.
                    
                    🔋 Reciclar metales reduce el 95% del consumo energético industrial.
                    
                    💡 Una botella de vidrio reciclada puede ahorrar energía suficiente 
                    para encender una bombilla por 4 horas.
                    """.trimIndent()
            )
        )

        fun listar_conceptos(): List<Informacion_Conceptos> {
            return conceptos
        }

        fun cargar_concepto(id: Int): Informacion_Conceptos? {
            return conceptos.find { it.id_concepto == id }
        }
    }
}
