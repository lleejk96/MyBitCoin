package com.example.coin.network

import com.example.coin.model.CurrentPriceList
import com.example.coin.model.RecentCoinPriceList
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApi {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList

    @GET("public/transaction_history/{coin}_KRW")
    suspend fun getRecentConPrice(@Path("coin") coin : String) : RecentCoinPriceList
}