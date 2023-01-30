package com.example.coin.view.intro

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.coin.R
import com.example.coin.databinding.ActivityIntroBinding
import com.example.coin.view.main.MainActivity
import com.example.coin.viewmodel.IntroViewModel
import timber.log.Timber

//splash screen 만들기
//handler -> 3초 뒤에 다른 액티비티로

class IntroActivity : AppCompatActivity() {
    private val viewModel : IntroViewModel by viewModels()
    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.tag("IntroActivity").d("onCreate")

        viewModel.checkFirstFlag()
        initData()
    }
    private fun initData(){
        viewModel.first.observe(this, Observer { first ->
            if (first){ // 처음 접속하는 유저가 아님
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else{ // 처음 접속하는 유저
                binding.fragmentContainerView.visibility =  View.VISIBLE
                binding.animationView.visibility = View.INVISIBLE
            }
        })
    }
}