package com.example.coin.model

data class CurrentPriceList(
    val status : String,
    val data : Map<String, Any>
)