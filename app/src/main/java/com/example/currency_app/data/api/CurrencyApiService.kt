package com.example.currency_app.data.api

import com.example.currency_app.data.model.Currency
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
@Root(name = "ValCurs")
data class ValCurs(
    @field:Attribute(name = "Date")
    var date: String? = null,

    @field:Attribute(name = "name")
    var name: String? = null,

    @field:ElementList(inline = true, name = "Valute")
    var currencies: MutableList<Currency> = mutableListOf()
)


interface CurrencyApiService {
    @GET("XML_daily.asp")
    suspend fun getCurrencyRates(): ValCurs
}

object RetrofitClient {
    private const val BASE_URL = "https://www.cbr.ru/scripts/"

    val apiService: CurrencyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }
}