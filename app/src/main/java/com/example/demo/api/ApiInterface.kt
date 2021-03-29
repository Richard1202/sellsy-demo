package com.example.demo.api

import com.example.demo.data.response.GetClientsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("0")
    fun getClients(@Field("io_mode") io_mode: String,
                   @Field("do_in") do_in: String) : Call<GetClientsResponse>
}