package com.example.currency_converter.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.currency_converter.models.ResponseRate

interface ExchangeApi {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String = "EUR"
    ): ResponseRate
}
