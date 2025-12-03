package com.example.currency_converter.models

data class ResponseRate(
    val base: String,
    val rates: Map<String, Double>
)
