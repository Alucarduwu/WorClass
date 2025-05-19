package com.example.worclass

import WhatsAppCloneApp
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worclass.data.model.database.AppDatabase
import com.example.worclass.data.model.database.DatabaseProvider
import com.example.worclass.ui.screens.AccountScreen
import com.example.worclass.ui.screens.BiometricScreen
import com.example.worclass.ui.screens.FavoriteAccountScreen
import com.example.worclass.ui.screens.HomeScreen
import com.example.worclass.ui.screens.MainMenuScreen
import com.example.worclass.ui.screens.ManageAccountScreen
import com.example.worclass.ui.screens.NotificationScreen
import com.example.worclass.ui.screens.TestScreen
import com.example.worclass.ui.theme.WorClassTheme
import com.example.workclass.ui.screens.ComponentsScreen
import com.example.workclass.ui.screens.LoginScreen
import com.example.workclass.ui.screens.ReporteFotoApp
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            database = DatabaseProvider.getDatabase(this)
            Log.d("debug-db", "Database loaded successfully")
        } catch (exception: Exception) {
            Log.d("debug-db", "ERROR: $exception")
        }

        enableEdgeToEdge()
        setContent {
            WorClassTheme {
                ComposeMultiScreenApp()
            }
        }
    }
}

@Composable
fun ComposeMultiScreenApp() {
    val navController = rememberNavController()
    SetUpNavGraph(navController = navController)
}

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "MainMenuScreen"
    ) {
        composable("MainMenuScreen") { MainMenuScreen(navController) }
        composable("HomeScreen") { HomeScreen(navController) }
        composable("TestScreen") { TestScreen(navController) }
        composable("WhatsAppClone") { WhatsAppCloneApp(navController) }
        composable("ComponentsScreen") { ComponentsScreen(navController) }
        composable("LoginScreen") { LoginScreen(navController) }
        composable( "NotificationScreen" ) { NotificationScreen(navController) }
        composable("camara") { ReporteFotoApp(navController) }
        composable("accounts_screen") { AccountScreen(navController) } // <- Corregido aquí
        composable("favorite_accounts_screen") { FavoriteAccountScreen(navController) }
        composable("manage_account_screen?id={id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ManageAccountScreen(navController = navController, id = id)
        }
        composable("biometric_screen"){
            val context = LocalContext.current
            BiometricScreen(navController, onAuthSuccess = {
                Toast.makeText(context,"Auth exitosa", Toast.LENGTH_SHORT).show()

            })
        }
    }
}

