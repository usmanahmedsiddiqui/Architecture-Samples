package com.example.core.common.error

import java.io.IOException

sealed class ApiException(message: String? = null): IOException(message) {
    object UnknownError: ApiException()
    data class ApiError(override val message: String?): ApiException(message)
    object IntervalServerError: ApiException()
}