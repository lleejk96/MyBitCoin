package com.example.coin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.database.enity.InterestCoinPriceEntity
import com.example.coin.datastore.MyDataStore
import com.example.coin.model.CurrentPrice
import com.example.coin.model.CurrentPriceResult
import com.example.coin.repository.DBRepository
import com.example.coin.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SelectViewModel : ViewModel() {
    private val networkRepository = NetworkRepository()
    private val dbRepository = DBRepository()
    private lateinit var currentPriceResultList: ArrayList<CurrentPriceResult>

    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    private val _savedCoinResult = MutableLiveData<String>()
    val savedCoinResult : LiveData<String>
        get() = _savedCoinResult

    fun getCurrentCoinList() = viewModelScope.launch {
        val result = networkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()
        for (coin in result.data){
            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[coin.key])
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)
                Timber.d(currentPriceResult.toString())

                currentPriceResultList.add(currentPriceResult)

            } catch (e: java.lang.Exception){
                Timber.e(e.message)
            }
        }
        _currentPriceResult.value = currentPriceResultList
    }

    fun setUpFirstFlag() = viewModelScope.launch {
        MyDataStore().setupFirstData()
    }

    fun saveSelectedCoinList(selectedCoinList : ArrayList<String>) = viewModelScope.launch (Dispatchers.IO) {
        for (coin in currentPriceResultList){
            val selected = selectedCoinList.contains(coin.coinName)
            val interestCoinPriceEntity = InterestCoinPriceEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded_24H,
                coin.coinInfo.acc_trade_value_24H,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            interestCoinPriceEntity.let {
                dbRepository.insertInterestCoinData(it)
            }
        }
        withContext(Dispatchers.Main){
            _savedCoinResult.value = "complete"
        }
    }
}