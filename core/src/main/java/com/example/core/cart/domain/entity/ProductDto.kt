package com.example.core.cart.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.product.domain.entity.Price
import com.example.core.product.domain.entity.Product


@Entity(tableName = "cart")
data class ProductDto(
    @PrimaryKey
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Price,
    var count: Int = 0
)

fun Product.toProductDto(count: Int) =
    ProductDto(
        id = this.id,
        title = this.name,
        thumbnail = this.thumbnail,
        price = this.price,
        count = count
    )