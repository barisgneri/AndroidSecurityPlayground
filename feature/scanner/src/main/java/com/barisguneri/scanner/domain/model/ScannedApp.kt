package com.barisguneri.scanner.domain.model

data class ScannedApp(
    val appName: String,
    val packageName: String,
    val signatureHash: String,
    val isMalicious: Boolean
)