package com.example.currency_app.repository

import com.example.currency_app.data.api.RetrofitClient
import com.example.currency_app.data.model.Currency

class CurrencyRepository {
    private val api = RetrofitClient.apiService

    suspend fun getCurrencyByCode(code: String): Currency? {

        return try {
            val response = api.getCurrencyRates()
            response.currencies.find { it.charCode == code }
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
}