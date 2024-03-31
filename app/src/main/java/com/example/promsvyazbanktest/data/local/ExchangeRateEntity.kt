package com.example.promsvyazbanktest.data.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ExchangeRateEntity: RealmObject {
    @PrimaryKey
    var date: String = ""
    var previousDate: String? = null
    var previousURL: String? = null
    var timestamp: String? = null
    var updateAt: Long? = null
}

class CurrencyEntity: RealmObject {
    @PrimaryKey
    var id: String = ""
    var exchangeRateId: String = ""
    var numCode: String? = null
    var charCode: String? = null
    var nominal: Int? = null
    var name: String? = null
    var value: Double? = null
    var previous: Double? = null
}