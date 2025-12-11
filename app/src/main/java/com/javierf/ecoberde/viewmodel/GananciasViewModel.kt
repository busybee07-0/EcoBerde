package com.javierf.ecoberde.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.javierf.ecoberde.data.model.ganancias.*

class GananciasViewModel : ViewModel() {

    private val calendario = Calendario()
    private val historial = HistorialG()
    private val calculadora = CalculoG()

    // 🔹 NUEVO: materiales agrupados por fecha (dd/MM/yyyy)
    private val materialesPorFecha =
        mutableStateMapOf<String, MutableList<Material_Ganancia>>()

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

    fun seleccionarFecha(fecha: String) {
        fechaSeleccionada = fecha
        calendario.seleccionarFecha(fecha)

        // Si ya existe un registro de ese día en el historial, lo cargamos
        val registroExistente = historialLista.firstOrNull { it.first == fecha }
        totalDia = registroExistente?.second
    }

    /**
     * Guarda un material en la fecha actualmente seleccionada.
     */
    fun agregarMaterial(material: Material_Ganancia) {
        if (fechaSeleccionada.isBlank()) return

        if (material.validar()) {
            val lista = materialesPorFecha.getOrPut(fechaSeleccionada) { mutableListOf() }
            lista.add(material)
        }
    }

    /**
     * Devuelve los materiales de una fecha dada.
     */
    private fun obtenerMaterialesDe(fecha: String): List<Material_Ganancia> {
        return materialesPorFecha[fecha].orEmpty()
    }

    /**
     * Calcula las ganancias del día (usa la fecha actual por defecto).
     */
    fun calcularGanancias(fecha: String = fechaSeleccionada) {
        if (fecha.isBlank()) {
            totalDia = 0.0
            detallesDia = emptyList()
            return
        }

        val materiales = obtenerMaterialesDe(fecha)

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

        // Guardar en historial (fecha → total)
        historial.agregarRegistro(fecha, total)
        historialLista = historial.obtenerHistorial()
    }

    fun cargarHistorial() {
        historialLista = historial.obtenerHistorial()
    }
}

