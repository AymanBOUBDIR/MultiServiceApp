package ma.enset.deviseconverter
import DrawerLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ma.enset.deviseconverter.ui.AppNavGraph
import ma.enset.deviseconverter.ui.theme.DeviseConverterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviseConverterAppTheme {
                val navController = rememberNavController()
                DrawerLayout(navController) {
                    AppNavGraph(navController)
                }
            }
        }

    }

}

@Composable
fun ConverterScreen() {
    var euroInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val history = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        TextField(
            value = euroInput,
            onValueChange = { euroInput = it },
            label = { Text("Montant en Euro") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val euro = euroInput.toDoubleOrNull()
            if (euro != null) {
                val dirham = euro * 11
                result = "$euro € → $dirham DH"
                history.add(result)
                euroInput = ""
            } else {
                result = "Veuillez entrer un montant valide"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Convertir en Dirham")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = result, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Historique des conversions:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(history) { item ->
                Text(text = item)
            }
        }
    }
}