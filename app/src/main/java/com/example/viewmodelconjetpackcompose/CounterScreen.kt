package com.example.viewmodelconjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Pantalla contenedora con estado (Stateful Composable).
 * Se encarga de obtener la instancia del ViewModel y conectar
 * el estado y los eventos con el diseño visual.
 */
@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    // Obtiene automáticamente la instancia del ViewModel asociada a esta pantalla.
    viewModel: CounterViewModel = viewModel()
) {
    // 'collectAsState()' escucha los cambios del 'StateFlow' del ViewModel.
    // 'by' permite acceder directamente al entero 'count' sin escribir '.value'.
    // Cada vez que cambia el contador, la pantalla se redibuja (recomposición) automáticamente.
    val count by viewModel.count.collectAsState()

    // Llama al Composable visual pasándole el estado actual y la función para incrementar.
    CounterContent(
        count = count,
        onIncrement = { viewModel.increment() }, // Evento enviado al ViewModel
        modifier = modifier
    )
}

/**
 * Pantalla visual sin estado (Stateless Composable / State Hoisting).
 * No conoce al ViewModel, solo recibe datos (count) y emite eventos (onIncrement).
 * Esto hace que sea reutilizable y fácil de probar en el Preview.
 */
@Composable
fun CounterContent(
    count: Int,               // Valor numérico a mostrar en pantalla
    onIncrement: () -> Unit,  // Callback/Lamba ejecutada al hacer clic en el botón
    modifier: Modifier = Modifier
) {
    // Column organiza a sus hijos de forma vertical (uno debajo de otro).
    Column(
        modifier = modifier.fillMaxSize(), // Ocupa todo el espacio de la pantalla
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Muestra el valor del contador iniciado en 0
        Text(
            text = "Contador: $count",
            style = MaterialTheme.typography.headlineMedium
        )

        // Espacio en blanco de 16dp entre el texto y el botón
        Spacer(modifier = Modifier.height(16.dp))

        // Botón interactivo que ejecuta 'onIncrement' al ser presionado
        Button(onClick = onIncrement) {
            Text(text = "Incrementar")
        }
    }
}

/**
 * Vista previa para visualizar la pantalla en Android Studio sin ejecutar la app.
 */
@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    CounterContent(
        count = 0,
        onIncrement = {}
    )
}
