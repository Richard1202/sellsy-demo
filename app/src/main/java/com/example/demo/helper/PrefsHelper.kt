package com.example.demo.helper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.demo.BuildConfig

class PrefsHelper(private val sp: SharedPreferences) {

    companion object {
        // Logged in user name
        const val PREF_USER_NAME = "pref_user_name"
        // Logged in user password
        const val PREF_USER_PASSWORD = "pref_user_password"

        fun shared(context: Context): PrefsHelper {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("sellsy",Context.MODE_PRIVATE)
            return PrefsHelper(sharedPreferences)
        }

    }

    var userName: String?
        get() {
            val value = sp.getString(PREF_USER_NAME, BuildConfig.DEFAULT_EMAIL)
            return if (value != null) value else null
        }
        set(value) {
            sp.edit().apply {
                if (value == null) {
                    remove(PREF_USER_NAME)
                } else {
                    putString(PREF_USER_NAME, value)
                }
            }.apply()
        }

    var password: String?
        get() {
            val value = sp.getString(PREF_USER_PASSWORD, BuildConfig.DEFAULT_PASSWORD)
            return if (value != null) value else null
        }
        set(value) {
            sp.edit().apply {
                if (value == null) {
                    remove(PREF_USER_PASSWORD)
                } else {
                    putString(PREF_USER_PASSWORD, value)
                }
            }.apply()
        }
}
