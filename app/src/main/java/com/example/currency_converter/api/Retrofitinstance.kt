package com.example.currency_converter.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ExchangeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.exchangerate.host/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeApi::class.java)
    }
}
