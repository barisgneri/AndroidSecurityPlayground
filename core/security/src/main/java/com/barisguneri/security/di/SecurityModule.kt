package com.barisguneri.security.di

import com.barisguneri.security.data.DeviceIntegrityProviderImpl
import com.barisguneri.security.domain.repository.DeviceIntegrityProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SecurityModule {

    @Binds
    abstract fun bindDeviceIntegrityProvider(
        deviceIntegrityProviderImpl: DeviceIntegrityProviderImpl
    ): DeviceIntegrityProvider


}