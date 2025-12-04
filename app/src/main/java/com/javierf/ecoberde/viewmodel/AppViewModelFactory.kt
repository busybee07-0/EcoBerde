package com.javierf.ecoberde.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javierf.ecoberde.data.database.EcoBerdeDatabase
import com.javierf.ecoberde.data.repository.MaterialRecicladoRepository
import com.javierf.ecoberde.data.repository.MaterialRepository

class AppViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val db = EcoBerdeDatabase.getDatabase(context)

        val materialRepo = MaterialRepository(db.materialDao())
        val recicladoRepo = MaterialRecicladoRepository(db.materialRecicladoDao())

        return when {
            modelClass.isAssignableFrom(MaterialViewModel::class.java) ->
                MaterialViewModel(materialRepo) as T

            modelClass.isAssignableFrom(MaterialRecicladoViewModel::class.java) ->
                MaterialRecicladoViewModel(recicladoRepo) as T

            else -> throw IllegalArgumentException("ViewModel desconocido")
        }
    }
}
