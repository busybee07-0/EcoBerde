package com.javierf.ecoberde.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.javierf.ecoberde.data.model.ganancias.*

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

        // No cargamos detalles (no estaban implementados originalmente)
        detallesDia = emptyList()
    }

    /**
     * Agrega un material a la lista temporal del día.
     */
    fun agregarMaterial(material: Material_Ganancia) {
        if (material.validar()) {
            registro.agregarMaterial(material)
        }
    }

    /**
     * Calcula las ganancias del día con todos los materiales agregados.
     */
    fun calcularGanancias() {
        val materiales = registro.obtenerMateriales()

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
        if (fechaSeleccionada.isNotBlank()) {
            historial.agregarRegistro(fechaSeleccionada, total)
            historialLista = historial.obtenerHistorial()
        }
    }

    /**
     * Cargar historial completo.
     */
    fun cargarHistorial() {
        historialLista = historial.obtenerHistorial()
    }
}




