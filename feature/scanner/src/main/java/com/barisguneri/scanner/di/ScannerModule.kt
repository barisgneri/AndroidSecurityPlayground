package com.barisguneri.scanner.di

import com.barisguneri.scanner.data.repository.ScannerRepositoryImpl
import com.barisguneri.scanner.domain.repository.ScannerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScannerModule {
    @Binds
    abstract fun bindScannerRepository(impl: ScannerRepositoryImpl): ScannerRepository

}