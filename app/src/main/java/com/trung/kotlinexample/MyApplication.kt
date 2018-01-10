package com.trung.kotlinexample

import android.app.Application
import android.content.Context
import com.trung.kotlinexample.config.ApiService

class MyApplication : Application() {

    fun getApiService(): ApiService = ApiService.Factory.create(this)

    companion object {
        fun with(context: Context): MyApplication = context.applicationContext as MyApplication
    }
}
