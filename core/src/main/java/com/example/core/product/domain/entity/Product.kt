package com.example.core.product.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val thumbnail: String,
    val price: Price
)