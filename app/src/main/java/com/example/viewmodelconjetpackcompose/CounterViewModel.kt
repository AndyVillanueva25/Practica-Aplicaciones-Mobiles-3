package com.example.viewmodelconjetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar el estado y la lógica de negocio del contador.
 * Sobrevive a los cambios de configuración (como la rotación de pantalla).
 */
class CounterViewModel : ViewModel() {

    // Estado privado y modificable internamente. Inicia en 0 como se solicita.
    private val _count = MutableStateFlow(0)

    // Estado público de solo lectura expuesto a la vista (UI).
    // Evita que la interfaz modifique directamente el estado sin pasar por la lógica del ViewModel.
    val count: StateFlow<Int> = _count.asStateFlow()

    /**
     * Incrementa el valor del contador en un uno (+1).
     */
    fun increment() {
        _count.value++
    }
}
