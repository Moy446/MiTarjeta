plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("com.google.relay") version ("0.3.12")
    id("com.google.gms.google-services")
    id("com.chaquo.python")
}

android {
    namespace = "com.bocchi.mitarjeta"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bocchi.mitarjeta"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //python libraries
        ndk {
            // On Apple silicon, you can omit x86_64.
            abiFilters += listOf("arm64-v8a", "x86_64")
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //google fonts
    implementation ("com.google.android.material:material:1.6.0")  // o la última versión disponible
    implementation ("androidx.core:core-ktx:1.9.0")  // para trabajar con las fuentes más fácilmente

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)

    //zxing escaner
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")

    //permisos
    implementation ("com.google.accompanist:accompanist-permissions:0.37.3")
}