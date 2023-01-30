package com.example.coin.repository

import com.example.coin.App
import com.example.coin.database.CoinPriceDatabase
import com.example.coin.database.enity.InterestCoinPriceEntity
import com.example.coin.model.SelectedCoinPriceEntity

class DBRepository {
    val context = App.getContext()
    val db = context?.let { CoinPriceDatabase.getDatabase(it) }

    //InsertCoin
    // 코인 전체 데이터 가져오기
    fun getAllInterestCoinData() = db?.interestCoinDao()?.getAllData()

    // 코인 데이터 넣기
    fun insertInterestCoinData(interestCoinPriceEntity: InterestCoinPriceEntity) = db?.interestCoinDao()?.insert(interestCoinPriceEntity)

    // 코인 데이터 업데이트
    fun updateInterestCoinData(interestCoinPriceEntity: InterestCoinPriceEntity) = db?.interestCoinDao()?.update(interestCoinPriceEntity)

    // 사용자가 관심있어하는 코인만 가져오기
    fun getAllInterestSelectedCoinData() = db?.interestCoinDao()?.getSelectedCoin()

    fun getAllCoinPriceData() = db?.selectedCoinPriceDao()?.getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) = db?.selectedCoinPriceDao()?.insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db?.selectedCoinPriceDao()?.getOneCoinData(coinName)
}