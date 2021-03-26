package com.example.demo.repository

import com.example.demo.BuildConfig
import com.example.demo.data.response.GetClientsResponse
import com.example.demo.data.state.GetClientsState
import com.example.demo.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ClientRepository {

    fun fetchClients(pageNum: Int, callback: Callback<GetClientsResponse>) {
        val params: String = "{\"method\":\"Client.getList\",\"params\":{\"pagination\": {\"pagenum\": 1, \"nbperpage\": ${BuildConfig.LIST_PAGE_SIZE}}}}"
        val call = RetrofitClient.apiInterface.getClients("json", params)
        call.enqueue(callback)
    }
}