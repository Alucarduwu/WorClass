package com.example.worclass.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainMenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text(text = "Main Menu")
        Button(onClick = {navController.navigate(route = "HomeScreen")})
        {
           Text(text = "Home Screen:)")
        }
        Button(onClick = {navController.navigate(route = "TestScreen")})
        {
            Text(text = "Test Screen:)")
        }
        Button(onClick = {navController.navigate(route = "WhatsAppClone")})
        {
            Text(text = "Wsp Screen:)")
        }
        Button(onClick = {navController.navigate(route = "ComponentsScreen")})
        {
            Text(text = "Comps Screen:)")
        }
        Button(onClick = {navController.navigate(route = "LoginScreen")})
        {
            Text(text = "Log Screen:)")
        }

    }



}
