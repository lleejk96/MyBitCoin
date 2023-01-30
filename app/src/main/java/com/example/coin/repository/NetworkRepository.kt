package com.example.coin.repository

import com.example.coin.network.BithumbApi
import com.example.coin.network.RetrofitInstance

class NetworkRepository {
    private val client = RetrofitInstance.getInstance().create(BithumbApi::class.java)
    suspend fun getCurrentCoinList() = client.getCurrentCoinList()
    suspend fun getInterestCoinPriceData(coin : String) = client.getRecentConPrice(coin)
}