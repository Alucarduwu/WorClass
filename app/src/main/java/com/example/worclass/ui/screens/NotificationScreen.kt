package com.example.worclass.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.worclass.`class`.NotificationWorker
import java.util.concurrent.TimeUnit

@Composable
fun NotificationScreen(navController: NavHostController) {
    val context = LocalContext.current
    var isDownloading by remember { mutableStateOf(false) }

    // Pedir permisos de notificación en Android 13+
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    (context as android.app.Activity),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Simulación de descarga en segundo plano",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    isDownloading = true

                    // Iniciar worker
                    val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                        .setInitialDelay(1, TimeUnit.SECONDS)
                        .build()

                    WorkManager.getInstance(context).enqueue(request)

                    Toast.makeText(context, "Descarga iniciada", Toast.LENGTH_SHORT).show()
                },
                enabled = !isDownloading
            ) {
                Text(if (isDownloading) "Descargando..." else "Iniciar descarga")
            }
        }
    }
}
