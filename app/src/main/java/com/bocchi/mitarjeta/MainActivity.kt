package com.bocchi.mitarjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val permission = rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.CAMERA
                )
            )
            LaunchedEffect (key1 = Unit ){
                permission.launchMultiplePermissionRequest()
            }
            MyAppNavigation()
        }
    }
}
