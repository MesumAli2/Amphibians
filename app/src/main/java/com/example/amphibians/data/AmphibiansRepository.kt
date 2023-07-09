package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class DefaultAmphibiansRepository(
    private val amphibianApiService : AmphibiansApiService
) : AmphibiansRepository{
    override suspend fun getAmphibians(): List<Amphibian> {
        return  amphibianApiService.getAmphibians()
    }

}