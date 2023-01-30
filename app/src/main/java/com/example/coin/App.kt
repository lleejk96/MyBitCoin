package com.example.coin

import android.app.Application
import android.content.Context
import timber.log.Timber

class App : Application() {
    companion object{
        private var instance : App? = null
        fun getContext() : Context? {
            return instance?.applicationContext
        }
    }
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}