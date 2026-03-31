package com.barisguneri.securityplayground.security

interface DeviceIntegrityProvider {
    fun isDeviceRooted(): Boolean
    fun isEmulator(): Boolean
}