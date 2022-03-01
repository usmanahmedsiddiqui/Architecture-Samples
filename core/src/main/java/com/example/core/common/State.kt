package com.example.core.common


sealed class State<T> {

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val throwable: Throwable) : State<T>()

    class Loading<T> : State<T>()

    companion object {

        fun <T> success(data: T): State<T> {
            return Success(data)
        }

        fun <T> error(throwable: Throwable): State<T> {
            return Error(throwable)
        }

        fun <T> loading(): State<T> {
            return Loading()
        }
    }

}