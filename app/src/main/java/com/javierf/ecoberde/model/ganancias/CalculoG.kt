package com.javierf.ecoberde.data.model.ganancias


class CalculoG {

    // Suma total del día
    fun calcular(lista: List<Material_Ganancia>): Double {
        return lista.sumOf { it.calcularValorTotal() }
    }
}
