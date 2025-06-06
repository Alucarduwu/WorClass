plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.worclass"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.worclass"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.0"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Core Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)

    // Material 3 Extras
    implementation(libs.androidx.material3.adaptive)

    implementation(libs.androidx.material3.window.size.class1.v120)


    // Accompanist
    implementation(libs.accompanist.swiperefresh)


    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Room (with KSP compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Networking (Retrofit + Gson + Logging)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation (libs.androidx.biometric)

    // Coil
    implementation(libs.coil.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

// WorkManager
// REVISA QUE NO LOS TENGAS REPETIDOS, USA CTRL + F
    implementation(libs.androidx.work.runtime.ktx)
    implementation (libs.androidx.work.runtime)




    val cameraxVersion = "1.3.1"
    implementation(libs.androidx.camera.core) // Lógica base
    implementation(libs.androidx.camera.camera2)// Conexión con la API Camera2
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view) // Necesario para la prewiew
    implementation(libs.androidx.navigation.compose.v277)
    implementation (libs.accompanist.permissions)
    implementation(libs.androidx.camera.video)
    implementation (libs.coil.compose.v222)
}
