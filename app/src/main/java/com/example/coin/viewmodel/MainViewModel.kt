package com.example.coin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.coin.database.enity.InterestCoinPriceEntity
import com.example.coin.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinPriceEntity>>

    fun getAllInterestCoinData() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoinData()?.asLiveData()

        selectedCoinList = coinList as LiveData<List<InterestCoinPriceEntity>>

    }

    fun updateInterestCoinData(interestCoinPriceEntity: InterestCoinPriceEntity) = viewModelScope.launch(Dispatchers.IO) {
        interestCoinPriceEntity.selected = !interestCoinPriceEntity.selected
        dbRepository.updateInterestCoinData(interestCoinPriceEntity)
    }
}