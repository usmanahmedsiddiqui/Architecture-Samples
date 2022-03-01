package com.example.core.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.cart.domain.entity.ProductDto
import com.example.core.common.data.local.converters.PriceConverter

@Database(entities = [ProductDto::class], version = 1, exportSchema = false)
@TypeConverters(PriceConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao


    companion object {
        const val DATABASE_NAME = "com.example.architecturesamples.database"
    }
}