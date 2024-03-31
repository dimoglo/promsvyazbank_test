package com.example.promsvyazbanktest.data

import com.example.promsvyazbanktest.data.mappers.toEntity
import com.example.promsvyazbanktest.data.mappers.toModel
import com.example.promsvyazbanktest.data.remote.ApiService
import com.example.promsvyazbanktest.domain.ExchangeRateRepository
import com.example.promsvyazbanktest.domain.ExchangeRateModel
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val realm: Realm,
    private val apiService: ApiService
): ExchangeRateRepository {
    override suspend fun getExchangeRate() = flow {
        try {
            val exchangeRate = apiService.getExchangeRate()
            lateinit var exchangeRateModel: ExchangeRateModel
            realm.writeBlocking {
                deleteAll()
                val exchangeRateEntity = exchangeRate.toEntity() ?:
                throw Exception("Exchange rate parsing failed")
                copyToRealm(exchangeRateEntity)
                val currencyList = exchangeRate.valute.map {
                    val valuteEntity = it.value.toEntity(exchangeRate.date!!) ?:
                    throw Exception("valute parsing failed")
                    copyToRealm(valuteEntity)
                    valuteEntity.toModel()
                }
                exchangeRateModel = exchangeRateEntity.toModel(currencyList)
            }
            emit(Result.success(exchangeRateModel))
        } catch (e: HttpException) {
            when (e.code()) {
                in 300..399 ->
                    emit(Result.failure(Exception("Перенаправление: ${e.message()}")))
                in 400..499 ->
                    emit(Result.failure(Exception("Ошибка клиента: ${e.message()}")))
                in 500..599 ->
                    emit(Result.failure(Exception("Ошибка сервера: ${e.message()}")))
                else ->
                    emit(Result.failure(Exception("Неизвестная ошибка HTTP: ${e.message()}")))
            }
        } catch (e: IOException) {
            emit(Result.failure(e))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }

    }

}