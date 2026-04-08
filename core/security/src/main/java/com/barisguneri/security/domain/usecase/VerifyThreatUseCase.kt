package com.barisguneri.security.domain.usecase

import javax.inject.Inject

class VerifyThreatUseCase @Inject constructor() {
    suspend operator fun invoke(hashToVerify: String): Boolean {
        //normalde repositoryden sunucudan
        // Mocklanmış zararlı yazılım hash list
        val knownMalwareHashes = listOf(
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"
        )

        return knownMalwareHashes.contains(hashToVerify)
    }
}