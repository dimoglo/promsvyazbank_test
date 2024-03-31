package com.example.promsvyazbanktest.domain

data class ExchangeRateModel (
    var date: String? = null,
    var previousDate: String? = null,
    var previousURL: String? = null,
    var timestamp: String? = null,
    var valutes: List<CurrencyModel>,
    var updateAt: Long? = null
)

data class CurrencyModel (
    var id: String? = null,
    var numCode: String? = null,
    var charCode: String? = null,
    var nominal: Int? = null,
    var name: String? = null,
    var value: Double? = null,
    var previous: Double? = null
)