package com.example.promsvyazbanktest.utils

sealed class ScreenState<out T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val errorMessage: String? = null
) {
    data object Loading: ScreenState<Nothing>(true)
    class Success<T>(data: T): ScreenState<T>(false, data)
    class Error<T>(errorMessage: String): ScreenState<T>(false, null, errorMessage)
}