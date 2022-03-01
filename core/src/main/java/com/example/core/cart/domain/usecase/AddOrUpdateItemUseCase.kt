package com.example.core.cart.domain.usecase

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AddOrUpdateItemUseCase(private val cartRepository: CartRepository) {

    suspend fun invoke(productDto: ProductDto): Flow<Unit> {

        val product = cartRepository.getSingleProduct(productDto.id).first()

        return product?.let {
            if (productDto.count == 0)
                cartRepository.deleteItem(productDto)
            else
                cartRepository.updateItem(productDto.id, productDto.count)
        } ?: kotlin.run {
            cartRepository.addItem(productDto)
        }
    }
}