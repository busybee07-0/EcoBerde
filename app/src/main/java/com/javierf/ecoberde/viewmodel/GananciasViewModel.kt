package com.javierf.ecoberde.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.javierf.ecoberde.data.model.ganancias.CalculoG
import com.javierf.ecoberde.data.model.ganancias.Calendario
import com.javierf.ecoberde.data.model.ganancias.DetalleG
import com.javierf.ecoberde.data.model.ganancias.HistorialG
import com.javierf.ecoberde.data.model.ganancias.Material_Ganancia
import com.javierf.ecoberde.data.model.ganancias.Registro_Materiales

class GananciasViewModel : ViewModel() {

    private val calendario = Calendario()
    private val registro = Registro_Materiales()
    private val historial = HistorialG()
    private val calculadora = CalculoG()

    // =================== ESTADOS USADOS EN LAS PANTALLAS =================== //

    // Fecha seleccionada por el usuario (FORMATO dd/MM/yyyy)
    var fechaSeleccionada by mutableStateOf("")
        private set

    // Total del día calculado
    var totalDia by mutableStateOf<Double?>(null)
        private set

    // Lista de detalles del día (por material)
    var detallesDia by mutableStateOf<List<DetalleG>>(emptyList())
        private set

    // Lista general del historial (fecha → total)
    var historialLista by mutableStateOf<List<Pair<String, Double>>>(emptyList())
        private set


    // ======================= FUNCIONES PRINCIPALES ========================= //

    /**
     * Guarda la fecha seleccionada por el usuario.
     */
    fun seleccionarFecha(fecha: String) {
        fechaSeleccionada = fecha
        calendario.seleccionarFecha(fecha)

        // Si ya existe un registro de ese día, cargarlo
        val registroExistente = historialLista.firstOrNull { it.first == fecha }
        totalDia = registroExistente?.second ?: 0.0

        // Los detalles se recalculan cuando el usuario pide "Calcular"
        detallesDia = emptyList()
    }

    /**
     * Agrega un material a la lista temporal del día.
     */
    fun agregarMaterial(material: Material_Ganancia) {
        // Si no hay fecha, no guardamos nada
        if (fechaSeleccionada.isBlank()) return

        if (material.validar()) {
            registro.agregarMaterial(fechaSeleccionada, material)
        }
    }

    /**
     * Calcula las ganancias del día con todos los materiales agregados
     * para la fecha seleccionada.
     */
    fun calcularGanancias() {

        // Si no hay fecha elegida, no calculamos nada
        if (fechaSeleccionada.isBlank()) {
            totalDia = 0.0
            detallesDia = emptyList()
            return
        }

        val materiales = registro.obtenerMateriales(fechaSeleccionada)

        if (materiales.isEmpty()) {
            totalDia = 0.0
            detallesDia = emptyList()
            return
        }

        // Calcular total
        val total = calculadora.calcular(materiales)
        totalDia = total

        // Crear lista de detalles
        detallesDia = materiales.map {
            DetalleG(
                tipoMaterial = it.tipo,
                cantidad = it.cantidad,
                valorParcial = it.calcularValorTotal()
            )
        }

        // Guardar en historial
        historial.agregarRegistro(fechaSeleccionada, total)
        historialLista = historial.obtenerHistorial()
    }

    /**
     * Cargar historial completo.
     */
    fun cargarHistorial() {
        historialLista = historial.obtenerHistorial()
    }
}

