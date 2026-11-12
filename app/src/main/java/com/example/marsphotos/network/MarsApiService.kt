package com.example.marsphotos.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

object MarsApi {
    val service: MarsApiService by lazy {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MarsApiService::class.java)
    }
}
