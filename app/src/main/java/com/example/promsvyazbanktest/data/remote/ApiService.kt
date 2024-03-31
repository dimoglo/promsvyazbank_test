package com.example.promsvyazbanktest.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("daily_json.js")
    suspend fun getExchangeRate(): ExchangeRateDto
}