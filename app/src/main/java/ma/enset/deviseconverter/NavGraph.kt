package ma.enset.deviseconverter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ma.enset.deviseconverter.BMIScreen
import ma.enset.deviseconverter.ConverterScreen
import ma.enset.deviseconverter.GameScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "converter") {
        composable("converter") {
            ConverterScreen(onNavigateToGame = { navController.navigate("game") })
        }
        composable("game") {
            GameScreen(onBack = { navController.popBackStack() })
        }
        composable("bmi") {
            BMIScreen(onBack = { navController.popBackStack() })
        }
    }
}