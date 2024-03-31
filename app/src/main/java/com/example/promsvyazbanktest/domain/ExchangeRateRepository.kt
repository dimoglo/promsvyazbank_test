package com.example.promsvyazbanktest.domain

import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getExchangeRate(): Flow<Result<ExchangeRateModel>>
}