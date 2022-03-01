package com.example.core.cart.domain.usecase

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class DeleteItemUseCase(private val cartRepository: CartRepository) {

    suspend fun invoke(productDto: ProductDto): Flow<Unit> {
        return cartRepository.deleteItem(productDto)
    }
}