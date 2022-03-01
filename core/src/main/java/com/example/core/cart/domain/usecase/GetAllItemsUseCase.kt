package com.example.core.cart.domain.usecase

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase(private val cartRepository: CartRepository) {

    fun invoke(): Flow<List<ProductDto>> {
        return cartRepository.getAllProducts()
    }
}