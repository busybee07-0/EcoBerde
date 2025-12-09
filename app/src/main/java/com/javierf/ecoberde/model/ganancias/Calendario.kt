package com.javierf.ecoberde.data.model.ganancias

// Clase muy sencilla, solo guarda la fecha seleccionada
class Calendario {

    var fechaSeleccionada: String = ""

    fun seleccionarFecha(f: String) {
        fechaSeleccionada = f
    }
}
