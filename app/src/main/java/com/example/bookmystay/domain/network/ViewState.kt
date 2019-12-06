package com.example.bookmystay.domain.network

class ViewState<T>(var status: Status, var data: T?, var error: Throwable?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun<T> success(data: T): ViewState<T> {
            return ViewState(
                Status.SUCCESS,
                data!!,
                null
            )
        }

        fun<T> error(error: Throwable?, data: T?): ViewState<T> {
            return ViewState<T>(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): ViewState<T> {
            return ViewState<T>(
                Status.LOADING,
                data,
                null
            )
        }
    }
}