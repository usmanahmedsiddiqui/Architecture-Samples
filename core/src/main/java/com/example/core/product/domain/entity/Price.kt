package com.example.core.product.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Price(
  val amount: Double,
  val currency: String
)