package com.barisguneri.securityplayground.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.barisguneri.scanner.worker.MalwareScanWorker
import com.barisguneri.security.domain.repository.DeviceIntegrityProvider
import com.barisguneri.securityplayground.presentation.ui.theme.AndroidSecurityPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var deviceIntegrityProvider: DeviceIntegrityProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBackgroundSecurityScan()
        enableEdgeToEdge()
        setContent {
            AndroidSecurityPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android ${deviceIntegrityProvider.isEmulator()}",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun startBackgroundSecurityScan() {
        // İstersen OneTimeWorkRequestBuilder yerine periyodik (PeriodicWorkRequestBuilder) da yapabilirsin.
        val workRequest = OneTimeWorkRequestBuilder<MalwareScanWorker>().build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidSecurityPlaygroundTheme {
        Greeting("Android")
    }
}