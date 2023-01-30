package com.example.coin.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.coin.R
import com.example.coin.background.GetCoinPriceRecentContractedWorkManager
import com.example.coin.databinding.ActivitySelectBinding
import com.example.coin.view.adapter.SelectRVAdapter
import com.example.coin.view.main.MainActivity
import com.example.coin.viewmodel.SelectViewModel
import java.util.concurrent.TimeUnit

class SelectActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectBinding
    private val viewModel : SelectViewModel by viewModels()
    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCurrentCoinList()
        initLayout()
        initData()
    }

    private fun initData(){
        viewModel.currentPriceResult.observe(this, Observer {
            selectRVAdapter = SelectRVAdapter(this, it)
            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

        })
        viewModel.savedCoinResult.observe(this, Observer {
            if (it.equals("complete")){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // 가장 처음으로 저장한 코인 정보가 시작되는 시점
                saveInterestCoinDataPeriodic()
            }
        })
    }
    private fun saveInterestCoinDataPeriodic(){
        val myWork = PeriodicWorkRequest.Builder(
            GetCoinPriceRecentContractedWorkManager::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "GetCoinPriceRecentContractedWorkManager",
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )
    }

    private fun initLayout(){
        binding.laterTextArea.setOnClickListener {
            // 화면 첫 진입
            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}