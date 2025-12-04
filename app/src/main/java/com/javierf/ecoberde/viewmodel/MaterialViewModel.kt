package com.javierf.ecoberde.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.javierf.ecoberde.data.database.EcoBerdeDatabase
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.data.repository.MaterialRepository
import com.javierf.ecoberde.ui.theme.EcoBerdeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MaterialViewModel(private val repository: MaterialRepository) : ViewModel() {


    private val _materiales = MutableStateFlow<List<Material>>(emptyList())
    val materiales: StateFlow<List<Material>> get() = _materiales


    fun cargarMateriales() {
        viewModelScope.launch {
            _materiales.value = repository.obtenerTodos()
        }
    }


    fun agregar(material: Material, onResult: () -> Unit) {
        viewModelScope.launch {
            repository.agregarMaterial(material)
            cargarMateriales()
            onResult()
        }
    }


    fun modificar(material: Material, onResult: () -> Unit) {
        viewModelScope.launch {
            repository.actualizarMaterial(material)
            cargarMateriales()
            onResult()
        }
    }


    fun eliminar(material: Material, onResult: () -> Unit) {
        viewModelScope.launch {
            repository.eliminarMaterial(material)
            cargarMateriales()
            onResult()
        }
    }


    suspend fun obtenerPorId(id: Long): Material? {
        return repository.obtenerPorId(id)
    }


    fun buscar(nombre: String) {
        viewModelScope.launch {
            _materiales.value = repository.buscarMaterial(nombre)
        }
    }
}


