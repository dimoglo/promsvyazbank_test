package com.example.promsvyazbanktest.utils

import com.example.promsvyazbanktest.utils.DateConstants.DATE_FORMAT_DEFAULT
import com.example.promsvyazbanktest.utils.DateConstants.DATE_FORMAT_RU
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConstants {
    const val DATE_FORMAT_RU = "HH:mm:ss dd.MM.yyyy"
    const val DATE_FORMAT_DEFAULT = "yyyy-MM-dd'T'HH:mm:ssXXX"
}

fun formatDate(dateStr: String): String? {

    val originalFormat = SimpleDateFormat(DATE_FORMAT_DEFAULT, Locale.getDefault())
    val targetFormat = SimpleDateFormat(DATE_FORMAT_RU, Locale.getDefault())

    val date = originalFormat.parse(dateStr)
    return date?.let { targetFormat.format(it) }
}

fun getDateString(timeStamp: Long): String {
    val sdf = SimpleDateFormat(DATE_FORMAT_RU, Locale.getDefault())
    return sdf.format(Date(timeStamp))
}
