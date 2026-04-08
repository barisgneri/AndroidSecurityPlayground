package com.barisguneri.security.domain.repository

interface DeviceIntegrityProvider {
    fun isDeviceRooted(): Boolean
    fun isEmulator(): Boolean
}