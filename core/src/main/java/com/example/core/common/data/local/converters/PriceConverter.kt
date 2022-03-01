package com.example.core.common.data.local.converters

import androidx.room.TypeConverter
import com.example.core.product.domain.entity.Price
import com.google.gson.Gson

class PriceConverter {

    @TypeConverter
    fun fromPrice(price: Price): String {
        return Gson().toJson(price)
    }

    @TypeConverter
    fun toPrice(json: String): Price {
        return Gson().fromJson(json, Price::class.java)
    }
}