package com.example.core.product.domain.usecase

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.entity.toProductDto
import com.example.core.cart.domain.repository.CartRepository
import com.example.core.product.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MapProductUseCase(private val cartRepository: CartRepository) {

    suspend fun invoke(list: List<Product>): Flow<List<ProductDto>> {

        val cartList = cartRepository.getAllProducts().first()

        return flow {
            emit(
                list.map { product ->
                    val existingItem = cartList.firstOrNull { it.id == product.id }
                    product.toProductDto(existingItem?.count ?: 0)
                }
            )
        }
    }
}