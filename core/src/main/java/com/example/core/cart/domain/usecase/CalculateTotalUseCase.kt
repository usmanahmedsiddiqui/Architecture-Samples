package com.example.core.cart.domain.usecase

import com.example.core.cart.domain.repository.CartRepository
import com.example.core.product.domain.entity.Price
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class CalculateTotalUseCase(private val cartRepository: CartRepository) {

    suspend fun invoke(): Flow<Price> {
        val cartList = cartRepository.getAllProducts().first()
        var total = 0.0
        var currency = "EUR"
        cartList.forEach {
            total += it.price.amount * it.count
            currency = it.price.currency
        }

        return flow { emit(Price(total, currency)) }

    }
}