package com.example.coin.database

import android.content.Context
import androidx.room.*
import com.example.coin.database.dao.InterestCoinDAO
import com.example.coin.database.dao.SelectedCoinPriceDAO
import com.example.coin.database.enity.InterestCoinPriceEntity
import com.example.coin.model.DataConverters
import com.example.coin.model.SelectedCoinPriceEntity


@Database(entities = [InterestCoinPriceEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DataConverters::class)
abstract class CoinPriceDatabase : RoomDatabase() {
    abstract fun interestCoinDao() : InterestCoinDAO
    abstract fun selectedCoinPriceDao() : SelectedCoinPriceDAO
    companion object{
        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(context: Context) : CoinPriceDatabase{
            return INSTANCE ?: synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}