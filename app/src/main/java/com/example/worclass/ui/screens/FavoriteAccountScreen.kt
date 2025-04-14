package com.example.worclass.ui.screens
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.worclass.data.model.database.AppDatabase
import com.example.worclass.data.model.database.DatabaseProvider
import com.example.worclass.data.model.model.AccountEntity
import com.example.worclass.ui.components.FavoriteAccountCard
import com.example.worclass.ui.components.TopBarComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun FavoriteAccountScreen(navController: NavController) {

    // Obtener la base de datos y DAO para las operaciones de base de datos
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val accountDao = db.accountDao()

    // Estado para almacenar las cuentas favoritas obtenidas de la base de datos
    var accountsdb by remember { mutableStateOf<List<AccountEntity>>(emptyList()) }

    // Obtener las cuentas de la base de datos en un hilo de IO
    LaunchedEffect(Unit) {
        accountsdb = withContext(Dispatchers.IO) {
            accountDao.getAll()  // Obtener todas las cuentas favoritas
        }
    }


    Column {

        TopBarComponent("Favorite Accounts", navController, "favorite_accounts_screen")


        val listState = rememberLazyListState()

        // Mostrar las cuentas favoritas en una lista
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {

            items(accountsdb) { accountdb ->
                FavoriteAccountCard(
                    accountdb.id ?: 0,
                    accountdb.name ?: "",
                    accountdb.username ?: "",
                    accountdb.password ?: "",
                    accountdb.description ?: "",
                    accountdb.imageURL ?: "",
                    onDeleteClick = {
                        // Eliminar
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                accountDao.delete(accountdb)

                                accountsdb = withContext(Dispatchers.IO) {
                                    accountDao.getAll()
                                }
                            } catch (exception: Exception) {

                                Log.d("debug-db", "Error: $exception")
                            }
                        }
                    }
                )
            }
        }
    }
}