package com.trung.kotlinexample.config

import android.content.Context
import android.content.SharedPreferences


class AppConfig private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences

    var session: String
        get() = sharedPreferences.getString(KEY_SESSION, "")
        set(session) = sharedPreferences.edit().putString(KEY_SESSION, session).apply()

    var userId: String
        get() = sharedPreferences.getString(KEY_USER_ID, "")
        set(userId) = sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()

    init {
        this.sharedPreferences = context.getSharedPreferences(Pref, Context.MODE_PRIVATE)
    }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private val Pref = "Pref"

        private val KEY_SESSION = "key_session"
        private val KEY_USER_ID = "key_user_id"
        private var appConfig: AppConfig? = null

        fun getInstance(context: Context): AppConfig {
            if (appConfig == null) {
                appConfig = AppConfig(context)
            }
            return appConfig as AppConfig
        }
    }
}
