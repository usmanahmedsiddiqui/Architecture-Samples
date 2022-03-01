package com.example.architecturesamples.common.util

import com.example.core.product.domain.entity.Price
import java.text.NumberFormat
import java.util.*

fun Price.format(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    formatter.currency = Currency.getInstance(currency)
    return formatter.format(amount)
}