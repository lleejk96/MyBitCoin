package com.example.coin.datastore

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.preferencesDataStore
import com.example.coin.App
import com.example.coin.database.enity.InterestCoinPriceEntity
import timber.log.Timber

class MyDataStore {
    private val context = App.getContext()
    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    }
    private val mDataStore : DataStore<Preferences>? = context?.dataStore
    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")

    //메인 액티비티로 갈 떄 TRUE 라고 변경

    // 처음 접속하는 유저가 아니면 -> TRUE
    // 처음 접속하는 유저이면 -> FALSE
    suspend fun setupFirstData(){
        mDataStore?.edit { preferences ->
            preferences[FIRST_FLAG] = true
        }
    }
    suspend fun getFirstData() : Boolean{
        var currentValue = false

        mDataStore?.edit { preferences ->
            currentValue = preferences[FIRST_FLAG] ?: false
        }
        Timber.d("current Value $currentValue")

        return currentValue
    }
}