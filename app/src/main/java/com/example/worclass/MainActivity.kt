package com.example.worclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worclass.ui.screens.CustomText
import com.example.worclass.ui.screens.HomeScreen
import com.example.worclass.ui.screens.MainMenuScreen
import com.example.worclass.ui.screens.ModifierExample2
import com.example.worclass.ui.screens.ModifierExample4
import com.example.worclass.ui.screens.TestScreen
import com.example.worclass.ui.screens.TextComposable
import com.example.worclass.ui.screens.picture
import com.example.worclass.ui.theme.WorClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorClassTheme {
                ComposeMultiScreenApp()
                /*WorClassTheme {

                Column() {
                    TextComposable(name = "xd")
                    TextComposable()
                    TextComposable()
                    TextComposable()
                }
                Row() {
                    TextComposable()
                    TextComposable()
                    TextComposable()
                    TextComposable()
                }
                Column() {
                    ModifierExample2()
                    ModifierExample4()
                    CustomText()
                    picture()
                }
            }}*/
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
    }
}

