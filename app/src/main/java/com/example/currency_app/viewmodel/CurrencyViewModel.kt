package com.example.currency_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_app.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val repository = CurrencyRepository()

    private val _rate = MutableStateFlow<String>("—")
    val rate = _rate.asStateFlow()

    fun loadRate(currencyCode: String) {

        viewModelScope.launch {

            val currency = repository.getCurrencyByCode(currencyCode)
            _rate.value = currency?.value ?: "Ошибка"
        }
    }
}