package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val amphibiansRepository : AmphibiansRepository
}

class DefaultAppContainer : AppContainer{
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    /**
     * DI implementation for Amphibians repository
     */
    override val amphibiansRepository: AmphibiansRepository
        get() = DefaultAmphibiansRepository(retrofitService)
}