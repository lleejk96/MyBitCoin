package com.example.coin.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coin.model.RecentCoinPriceList
import com.example.coin.model.SelectedCoinPriceEntity
import com.example.coin.repository.DBRepository
import com.example.coin.repository.NetworkRepository
import java.util.*


class GetCoinPriceRecentContractedWorkManager(val context : Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {
    private val dbRepository = DBRepository()
    private val networkRepository = NetworkRepository()
    override suspend fun doWork(): Result {
        return Result.success()
    }

    suspend fun getAllInterestSelectedCoinData(){
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        if (selectedCoinList != null) {
            for (coinData in selectedCoinList){
                val recentCoinPriceList = networkRepository.getInterestCoinPriceData(coinData.coinNam)
                saveSelectedCoinPrice(coinData.coinNam, recentCoinPriceList, Calendar.getInstance().time)
            }
        }
    }

    fun saveSelectedCoinPrice(coinName : String, recentCoinPriceList: RecentCoinPriceList, timeStamp : Date){
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_data,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp
        )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)
    }
}