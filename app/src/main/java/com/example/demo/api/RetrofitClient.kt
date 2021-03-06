package com.example.demo.api

import com.example.demo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        val authInterceptor = Oauth1SigningInterceptor(::getOauthKeys)
        okhttpClient.addInterceptor(authInterceptor)

        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }

    private fun getOauthKeys() = OauthKeys(
        consumerKey = BuildConfig.CONSUMER_TOEKN,
        consumerSecret = BuildConfig.CONSUMER_SECRET,
        accessToken = BuildConfig.USER_TOKEN,
        accessSecret = BuildConfig.USER_SECRET
    )
}
