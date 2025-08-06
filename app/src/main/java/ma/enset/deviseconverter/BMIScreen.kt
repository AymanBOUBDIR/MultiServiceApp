package ma.enset.deviseconverter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun BMIScreen(onBack: () -> Unit) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf("") }
    var imageRes by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculateur d'IMC", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Poids (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Taille (m)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val w = weight.toDoubleOrNull()
                val h = height.toDoubleOrNull()
                if (w != null && h != null && h > 0) {
                    val result = w / (h * h)
                    bmi = result
                    when {
                        result < 18.5 -> {
                            category = "Maigreur"
                            imageRes = R.drawable.maigre
                        }
                        result < 25 -> {
                            category = "Corpulence normale"
                            imageRes = R.drawable.normal
                        }
                        result < 30 -> {
                            category = "Surpoids"
                            imageRes = R.drawable.surpoids
                        }
                        result < 35 -> {
                            category = "Obésité modérée"
                            imageRes = R.drawable.obese
                        }
                        else -> {
                            category = "Obésité sévère ou morbide"
                            imageRes = R.drawable.t_obese
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Calculer l'IMC")
        }

        Spacer(modifier = Modifier.height(16.dp))

        bmi?.let {
            Text("Votre IMC : %.2f".format(it), style = MaterialTheme.typography.bodyLarge)
            Text("Catégorie : $category", style = MaterialTheme.typography.bodyMedium)
            imageRes?.let { res ->
                Spacer(modifier = Modifier.height(8.dp))
                Image(painter = painterResource(id = res), contentDescription = category)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Retour")
        }
    }
}