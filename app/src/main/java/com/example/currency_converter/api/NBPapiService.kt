package com.example.currency_converter.api

import retrofit2.http.GET

data class NbpResponse(val rates: List<Rate>)
data class Rate(val currency: String, val code: String, val mid: Double)

interface NbpApiService {
    @GET("api/exchangerates/tables/A?format=json")
    suspend fun getExchangeRates(): List<NbpResponse>
}