package com.example.promsvyazbanktest.data.mappers

import com.example.promsvyazbanktest.data.local.CurrencyEntity
import com.example.promsvyazbanktest.data.local.ExchangeRateEntity
import com.example.promsvyazbanktest.data.remote.CurrencyDto
import com.example.promsvyazbanktest.data.remote.ExchangeRateDto
import com.example.promsvyazbanktest.domain.CurrencyModel
import com.example.promsvyazbanktest.domain.ExchangeRateModel

fun ExchangeRateDto.toEntity(): ExchangeRateEntity? {
    if(this.date == null)
        return null
    return ExchangeRateEntity().apply {
        date = this@toEntity.date
        previousDate = this@toEntity.previousDate
        previousURL = this@toEntity.previousURL
        timestamp = this@toEntity.timestamp
        updateAt = System.currentTimeMillis()
    }
}

fun CurrencyDto.toEntity(exchangeRateDate: String): CurrencyEntity? {
    if(this.id == null)
        return null
    return CurrencyEntity().apply {
        id = this@toEntity.id
        exchangeRateId = exchangeRateDate
        numCode = this@toEntity.numCode
        charCode = this@toEntity.charCode
        nominal = this@toEntity.nominal
        name = this@toEntity.name
        value = this@toEntity.value
        previous = this@toEntity.previous
    }
}

fun ExchangeRateEntity.toModel(valutes: List<CurrencyModel>): ExchangeRateModel {
    return ExchangeRateModel(
        date = this.date,
        previousDate = this.previousDate,
        previousURL = this.previousURL,
        timestamp = this.timestamp,
        valutes = valutes,
        updateAt = this.updateAt
    )
}

fun CurrencyEntity.toModel(): CurrencyModel {
    return CurrencyModel(
        id = this.id,
        numCode = this.numCode,
        charCode = this.charCode,
        nominal = this.nominal,
        name = this.name,
        value = this.value,
        previous = this.previous,
    )
}