package com.javierf.ecoberde.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.javierf.ecoberde.data.model.ganancias.*
import com.javierf.ecoberde.model.ganancias.Material_Ganancia
import java.text.SimpleDateFormat
import java.util.*

class GananciasViewModel : ViewModel() {

    // Manejo de fecha seleccionada
    private val calendario = Calendario()

    // Lista temporal de materiales agregados para un día
    private val registro = Registro_Materiales()

    // Historial con totales por fecha
    private val historial = HistorialG()

    // Calculadora del total
    private val calculadora = CalculoG()

    // ---------------- ESTADOS USADOS POR LAS UI ---------------- //

    // Fecha seleccionada en formato dd/MM/yyyy
    var fechaSeleccionada by mutableStateOf("")
        private set

    // Total del día (null = aún no calculado)
    var totalDia by mutableStateOf<Double?>(null)
        private set

    // Lista del detalle por material
    var detallesDia by mutableStateOf<List<DetalleG>>(emptyList())
        private set

    // Historial para la pantalla de historial
    var historialLista by mutableStateOf<List<Pair<String, Double>>>(emptyList())
        private set


    // -----------------------------------------------------------
    //  FECHA SELECCIONADA
    // -----------------------------------------------------------
    fun seleccionarFecha(millisUTC: Long) {
        // Convertir millis del DatePicker a zona horaria local (CORRIGE EL DÍA MENOS)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = millisUTC
        }

        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaLocal = formato.format(calendar.time)

        fechaSeleccionada = fechaLocal
        calendario.seleccionarFecha(fechaLocal)

        // Si ya existía registro en historial, cargarlo
        val guardado = historialLista.firstOrNull { it.first == fechaLocal }
        totalDia = guardado?.second ?: 0.0

        detallesDia = emptyList()
    }


    // -----------------------------------------------------------
    // AGREGAR MATERIAL
    // -----------------------------------------------------------
    fun agregarMaterial(material: Material_Ganancia) {
        if (material.validar()) {
            registro.agregarMaterial(material)
        }
    }


    // -----------------------------------------------------------
    // CALCULAR GANANCIAS
    // -----------------------------------------------------------
    fun calcularGanancias() {
        val materiales = registro.obtenerMateriales()

        if (materiales.isEmpty()) {
            totalDia = 0.0
            detallesDia = emptyList()
            return
        }

        val total = calculadora.calcular(materiales)
        totalDia = total

        detallesDia = materiales.map {
            DetalleG(
                tipoMaterial = it.tipo,
                cantidad = it.cantidad,
                valorParcial = it.calcularValorTotal()
            )
        }

        if (fechaSeleccionada.isNotBlank()) {
            historial.agregarRegistro(fechaSeleccionada, total)
            historialLista = historial.obtenerHistorial()
        }
    }


    // -----------------------------------------------------------
    // CARGAR HISTORIAL
    // -----------------------------------------------------------
    fun cargarHistorial() {
        historialLista = historial.obtenerHistorial()
    }
}

