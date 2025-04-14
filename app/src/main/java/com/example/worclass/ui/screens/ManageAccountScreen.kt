package com.example.worclass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.ui.components.TopBarComponent

@Composable
fun ManageAccountScreen(
    navController: NavController,
    id: Int?
) {
    val account = remember { mutableStateOf(AccountModel()) }
    var buttonText by remember { mutableStateOf("Save account") }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .fillMaxSize()
    ) {
        TopBarComponent("Add account", navController, "manage_account_screen")

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = { Text("Account name") },
            onValueChange = { account.value = account.value.copy(name = it) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.username,
            maxLines = 1,
            label = { Text("Account username") },
            onValueChange = { account.value = account.value.copy(username = it) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.password,
            maxLines = 1,
            label = { Text("Account password") },
            onValueChange = { account.value = account.value.copy(password = it) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.description,
            maxLines = 1,
            label = { Text("Account description") },
            onValueChange = { account.value = account.value.copy(description = it) }
        )

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            onClick = {
                if (
                    account.value.name.isBlank() ||
                    account.value.username.isBlank() ||
                    account.value.password.isBlank() ||
                    account.value.description.isBlank()
                ) {
                    buttonText = "Faltan campos"
                } else {
                    buttonText = "Agregado con éxito"
                }
            }
        ) {
            Text(buttonText)
        }
    }
}
