package com.barisguneri.securityplayground.security

import android.os.Build
import java.io.File

class DeviceIntegrityProviderImpl : DeviceIntegrityProvider {

    override fun isDeviceRooted(): Boolean {
        return checkTestKeys() || checkRootFiles() || checkSuCommand()
    }

    override fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT)
    }

    // --- ROOT DETECTION ---

    // Custom ROM'lar genelde test-keys kullanır
    private fun checkTestKeys(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootFiles(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    private fun checkSuCommand(): Boolean {
        return try {
            Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            true
        } catch (e: Exception) {
            false
        }
    }
}