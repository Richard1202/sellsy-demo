package com.example.demo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.BuildConfig
import com.example.demo.data.model.Client
import com.example.demo.data.response.GetClientsResponse
import com.example.demo.repository.ClientRepository
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ClientsViewModel : ViewModel() {

    val clients = MutableLiveData<MutableList<Client>>().apply { value = mutableListOf() }

    val message = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()


    fun fetchClients(index: Int) {
        loading.value = true

        val pageNum: Int = index / BuildConfig.LIST_PAGE_SIZE

        ClientRepository.fetchClients(pageNum, object: Callback<GetClientsResponse> {
            override fun onFailure(call: Call<GetClientsResponse>, error: Throwable) {
                loading.value = false
                message.value = error.message
            }

            override fun onResponse(call: Call<GetClientsResponse>, response: Response<GetClientsResponse>) {
                loading.value = false

                val data = response.body()?.let { it } ?: return

                if (data.error.isNotEmpty()) {
                    message.value = data.error
                }

                try {
                    val result = data.response.getAsJsonObject("result")
                    val entries: Set<Map.Entry<String?, JsonElement?>> = result.entrySet()

                    val list: MutableList<Client> = clients.value?.toMutableList() ?: mutableListOf()

                    for ((key, value) in entries) {
                        val client = Gson().fromJson(value, Client::class.java)
                        list.add(client)
                    }

                    clients.value = list

                } catch (e: Exception) {
                    message.value = e.message
                }
            }
        })
    }
}