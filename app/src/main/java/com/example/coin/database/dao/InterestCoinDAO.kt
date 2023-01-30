package com.example.coin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coin.database.enity.InterestCoinPriceEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InterestCoinDAO {

    // getAllData
    // room 과 flow의 관계 학습!! -> notion 정리
    // 데이터의 변경 사항을 감지하기 효과

    @Query("SELECT * FROM interest_coin_table")
    fun getAllData() : Flow<List<InterestCoinPriceEntity>>

    //insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinPriceEntity : InterestCoinPriceEntity)

    //update
    // 사용자가 코인 데이터를 선택했다가 다시 취소할 수도 있고, 반대로 선택안된것을 선택할 수도 있게 함
    @Update
    fun update(interestCoinPriceEntity: InterestCoinPriceEntity)

    // getSelectCoinList -> 내가 관심이어한 코인 데이터를 가져오는 것
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedCoin(selected : Boolean = true) : List<InterestCoinPriceEntity>
}