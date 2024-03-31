package com.example.promsvyazbanktest.data.remote

import com.google.gson.annotations.SerializedName

data class ExchangeRateDto(
    @SerializedName("Date") val date: String? = null,
    @SerializedName("PreviousDate") val previousDate: String? = null,
    @SerializedName("PreviousURL") val previousURL: String? = null,
    @SerializedName("Timestamp") val timestamp: String? = null,
    @SerializedName("Valute") val valute: Map<String, CurrencyDto>
)

data class CurrencyDto(
    @SerializedName("ID") val id: String? = null,
    @SerializedName("NumCode") val numCode: String? = null,
    @SerializedName("CharCode") val charCode: String? = null,
    @SerializedName("Nominal") val nominal: Int? = null,
    @SerializedName("Name") val name: String? = null,
    @SerializedName("Value") val value: Double? = null,
    @SerializedName("Previous") val previous: Double? = null
)