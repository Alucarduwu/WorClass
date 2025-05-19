package com.example.worclass.ui.screens


import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController

@Composable
fun BiometricScreen(navCloneable: NavHostController, onAuthSuccess: () -> Unit) {
    val context = LocalContext.current
    val activity = context as FragmentActivity
    val executor = remember { ContextCompat.getMainExecutor(context) }

    var authError by remember { mutableStateOf<String?>(null) }
    var authTriggered by remember { mutableStateOf(false) }

    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación")
            .setSubtitle("Inicia sesión con tu huella o PIN")
            .setNegativeButtonText("Cancelar")
            .build()
    }

    val biometricManager = BiometricManager.from(context)
    val canAuthenticate = biometricManager.canAuthenticate(
        BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
    )

    val currentOnAuthSuccess by rememberUpdatedState(newValue = onAuthSuccess)

    LaunchedEffect(Unit) {
        if (!authTriggered && canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            authTriggered = true
            val biometricPrompt = BiometricPrompt(
                activity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        currentOnAuthSuccess()
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        authError = "Autenticación fallida. Intenta de nuevo."
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        authError = "Error: $errString"
                    }
                }
            )

            biometricPrompt.authenticate(promptInfo)
        } else if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
            authError = "Tu dispositivo no soporta autenticación biométrica."
        }
    }

    // UI principal
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Autenticando...", style = MaterialTheme.typography.titleLarge)

            authError?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

