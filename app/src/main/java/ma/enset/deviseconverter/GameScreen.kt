package ma.enset.deviseconverter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(onBack: () -> Unit) {
    var secret by remember { mutableStateOf((1..100).random()) }
    var guess by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(1) }
    val history = remember { mutableStateListOf<String>() }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Devinez le nombre entre 1 et 100", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Votre essai") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val number = guess.toIntOrNull()
                if (number != null) {
                    history.add("Essai $attempts → $number")
                    when {
                        number < secret -> feedback = "Trop petit"
                        number > secret -> feedback = "Trop grand"
                        else -> {
                            feedback = "Bravo !"
                            score += 5
                            secret = (1..100).random()
                            attempts = 0
                            history.clear()
                        }
                    }
                    attempts++
                    guess = ""
                } else {
                    feedback = "Veuillez entrer un nombre valide"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Valider")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Feedback: $feedback")
        Text("Score: $score")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(history) { item ->
                Text(item)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}