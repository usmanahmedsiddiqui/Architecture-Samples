package com.example.core.cart.domain.usecase

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetSingleItemUseCase(private val cartRepository: CartRepository) {

    fun invoke(productId: String): Flow<ProductDto?> {
        return cartRepository.getSingleProduct(productId)
    }
}