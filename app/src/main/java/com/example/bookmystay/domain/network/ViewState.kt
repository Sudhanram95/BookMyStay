package com.example.bookmystay.domain.network

sealed class ViewState<T> {
    class Loading<T>(): ViewState<T>()
    class Success<T>(val data: T): ViewState<T>()
    class Error<T>(val error: Throwable): ViewState<T>()
}