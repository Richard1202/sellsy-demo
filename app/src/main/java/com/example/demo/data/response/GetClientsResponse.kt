package com.example.demo.data.response

import com.google.gson.JsonObject

data class GetClientsResponse(
    val error: String,
    val status: String,
    val response: JsonObject
)