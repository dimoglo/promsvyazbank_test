package com.example.promsvyazbanktest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.promsvyazbanktest.domain.ExchangeRateModel
import com.example.promsvyazbanktest.domain.ExchangeRateRepository
import com.example.promsvyazbanktest.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ExchangeRateRepository
): ViewModel() {

    companion object {
        const val UPDATE_PERIOD = 30000L
    }

    private val _state = MutableStateFlow<ScreenState<ExchangeRateModel>>(ScreenState.Loading)
    val state: StateFlow<ScreenState<ExchangeRateModel>> = _state

    private var periodicJob: Job? = null

    fun startPeriodicUpdate() {
        periodicJob = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                getExchangeRate()
                delay(UPDATE_PERIOD)
            }
        }
    }

    fun stopPeriodicUpdate() {
        periodicJob?.cancel()
        periodicJob = null
    }

    private fun getExchangeRate(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getExchangeRate().collect{
                    when{
                        it.isSuccess ->
                            _state.emit(ScreenState.Success(it.getOrThrow()))
                        it.isFailure ->
                            throw Exception(it.exceptionOrNull())
                    }
                }
            } catch (e: Exception) {
                _state.emit(ScreenState.Error(e.message ?: "Unknown Error"))
            }
        }
    }
}