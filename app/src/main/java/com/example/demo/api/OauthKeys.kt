package com.example.demo.api

data class OauthKeys(
    val consumerKey: String,
    val consumerSecret: String,
    val accessToken: String? = null,
    val accessSecret: String? = null
)