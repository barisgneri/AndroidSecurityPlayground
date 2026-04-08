package com.barisguneri.scanner.domain.repository

import com.barisguneri.scanner.domain.model.ScannedApp

interface ScannerRepository{
    suspend fun scanInstalledApps(): List<ScannedApp>
}