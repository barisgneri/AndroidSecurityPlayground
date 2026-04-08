package com.barisguneri.security.domain

interface DeviceIntegrityProvider {
    fun isDeviceRooted(): Boolean
    fun isEmulator(): Boolean
}