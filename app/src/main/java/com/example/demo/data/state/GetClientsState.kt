package com.example.demo.data.state

import com.example.demo.data.model.Client

data class GetClientsState (
    val message: String? = null,
    val items: List<Client>? = null
)