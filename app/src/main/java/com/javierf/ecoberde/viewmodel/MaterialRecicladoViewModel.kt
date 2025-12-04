package com.javierf.ecoberde.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javierf.ecoberde.data.entities.MaterialReciclado
import com.javierf.ecoberde.data.repository.MaterialRecicladoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MaterialRecicladoViewModel(private val repo: MaterialRecicladoRepository) : ViewModel() {

    private val _historial = MutableStateFlow<List<MaterialReciclado>>(emptyList())
    val historial: StateFlow<List<MaterialReciclado>> = _historial

    fun registrar(registro: MaterialReciclado, onDone: (Long) -> Unit = {}) {
        viewModelScope.launch {
            val id = repo.registrarReciclaje(registro)
            cargarHistorial()
            onDone(id)
        }
    }

    fun cargarHistorial() {
        viewModelScope.launch {
            _historial.value = repo.obtenerHistorial()
        }
    }

    fun buscarPorFecha(fecha: String) {
        viewModelScope.launch {
            _historial.value = repo.obtenerPorFecha(fecha)
        }
    }
}
