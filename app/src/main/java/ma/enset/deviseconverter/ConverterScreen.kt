package ma.enset.deviseconverter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConverterScreen(onNavigateToGame: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    var convertedAmount by remember { mutableStateOf("") }
    var conversionRate by remember { mutableStateOf(10.0) } // Example: 1 USD = 10 MAD

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Convertisseur de devises", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Montant en USD") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val input = amount.toDoubleOrNull()
                if (input != null) {
                    convertedAmount = "%.2f MAD".format(input * conversionRate)
                } else {
                    convertedAmount = "Veuillez entrer un montant valide"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Résultat: $convertedAmount")

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToGame,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Jouer au jeu de hasard")
        }
    }
}