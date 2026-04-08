package com.barisguneri.scanner.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.barisguneri.scanner.domain.model.ScannedApp
import com.barisguneri.scanner.domain.repository.ScannerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class ScannerRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ScannerRepository {

    override suspend fun scanInstalledApps(): List<ScannedApp> = withContext(Dispatchers.IO) {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val scannedApps = mutableListOf<ScannedApp>()

        for (appInfo in apps) {
            if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0) {
                val appName = packageManager.getApplicationLabel(appInfo).toString()
                val apkPath = appInfo.sourceDir

                val hash = calculateSHA256(File(apkPath))

                scannedApps.add(
                    ScannedApp(
                        appName = appName,
                        packageName = appInfo.packageName,
                        signatureHash = hash,
                        isMalicious = false
                    )
                )
            }
        }
        return@withContext scannedApps
    }

    private fun calculateSHA256(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val bytes =
                file.readBytes() // Büyük APK'lar için chunk (parça parça) okuma yapılabilir, playground için bu yeterli.
            val hashBytes = digest.digest(bytes)
            hashBytes.joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            "HASH_ERROR"
        }
    }
}