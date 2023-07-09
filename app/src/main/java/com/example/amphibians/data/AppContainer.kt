package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val amphibiansRepository : AmphibiansRepository
}

class DefaultAppContainer : AppContainer{
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
    private val moshi = Moshi. Builder ()
        .add (com.squareup.moshi.KotlinJsonAdapterFactory())
        .build()

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