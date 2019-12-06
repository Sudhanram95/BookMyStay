package com.example.bookmystay.domain.network

interface NetworkCallback {
    fun onSuccess(response: Any)
    fun onError(error: Throwable)
}