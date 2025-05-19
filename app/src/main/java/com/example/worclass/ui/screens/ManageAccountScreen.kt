package com.example.worclass.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.viewmodel.AccountViewModel
import com.example.worclass.ui.components.TopBarComponent
@Composable
fun ManageAccountScreen(
    navController: NavController,
    id: Int? = null,
    viewModel: AccountViewModel = viewModel()
) {
    val account = remember { mutableStateOf(AccountModel()) }
    val context = LocalContext.current

    // Obtener datos si estamos editando
    LaunchedEffect(id) {
        if (id != null) {
            viewModel.getAccount(id) { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        account.value = it
                    }
                } else {
                    Log.d("debug", "Error getting account")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .fillMaxSize()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = {
                Text("Account Name", color = MaterialTheme.colorScheme.onBackground)
            },
            onValueChange = { account.value = account.value.copy(name = it) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.username,
            maxLines = 1,
            label = {
                Text("Account Username", color = MaterialTheme.colorScheme.onBackground)
            },
            onValueChange = { account.value = account.value.copy(username = it) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.password,
            maxLines = 1,
            label = {
                Text("Account Password", color = MaterialTheme.colorScheme.onBackground)
            },
            onValueChange = { account.value = account.value.copy(password = it) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = account.value.description,
            maxLines = 1,
            label = {
                Text("Account Description", color = MaterialTheme.colorScheme.onBackground)
            },
            onValueChange = { account.value = account.value.copy(description = it) }
        )

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            onClick = {
                if (id == null) {
                    TryAccount(
                        account.value.name,
                        account.value.username,
                        account.value.password,
                        account.value.description,
                        viewModel,
                        context
                    )
                } else {
                    if (account.value.name.isEmpty() || account.value.username.isEmpty()
                        || account.value.password.isEmpty() || account.value.description.isEmpty()
                    ) {
                        Toast.makeText(context, "Error, please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.updateAccount(id, account.value) { response ->
                            Toast.makeText(context, "Account updated successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        ) {
            Text(if (id == null) "Save Account" else "Update Account")
        }

        // Solo muestra el botón de eliminar si se está editando
        if (id != null) {
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onClick = {
                    viewModel.deleteAccount(id) { response ->
                        Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            ) {
                Text("Delete account")
            }
        }
    }
}

// Crear cuenta solo si los campos son válidos
fun TryAccount(
    name: String,
    username: String,
    password: String,
    description: String,
    viewModel: AccountViewModel,
    context: Context
) {
    if (name == "" || username == "" || password == "" || description == "") {
        Toast.makeText(
            context,
            "Error, please add all data",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        val addAccount = AccountModel(0, name, username, password, description)
        viewModel.createAccount(addAccount) { jsonResponse ->
            val createStatus = jsonResponse?.get("addAccount")?.asString
            Toast.makeText(
                context,
                "Account added successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}