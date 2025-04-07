package com.example.worclass.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.viewmodel.AccountViewModel
import com.example.worclass.ui.components.AccountCardComponent
import com.example.worclass.ui.components.AccountDetailCardComponent
import com.example.worclass.ui.components.TopAppBarComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController, viewModel: AccountViewModel = viewModel()) {
    var accounts by remember { mutableStateOf<List<AccountModel>>(emptyList()) }
    var showButtonSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var accountDetail by remember { mutableStateOf<AccountModel?>(null) }

    Column {
        TopAppBarComponent("Accounts", navController, "accounts_screen")

        LaunchedEffect(Unit) {
            viewModel.getAccounts { response ->
                response?.let {
                    if (it.isSuccessful) {
                        accounts = response.body() ?: emptyList()
                    } else {
                        Log.d("debug", "Failed to load data")
                    }
                }
            }
        }

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(accounts) { account ->
                AccountCardComponent(
                    account.id,
                    account.name,
                    account.username,
                    account.imageURL.toString(),

                    ) {
                    viewModel.getAccount(account.id) { response ->
                        if (response.isSuccessful) {
                            accountDetail = response.body()
                        }
                    }
                    showButtonSheet = true
                }
            }
        }
    }

    if (showButtonSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = { showButtonSheet = false },
            sheetState = sheetState
        ) {
            AccountDetailCardComponent(
                accountDetail?.id ?: 0,
                accountDetail?.name ?: "",
                accountDetail?.username ?: "",
                accountDetail?.password ?: "",
                accountDetail?.imageURL ?: "",
                accountDetail?.description ?: ""
            )
        }
    }
    }

