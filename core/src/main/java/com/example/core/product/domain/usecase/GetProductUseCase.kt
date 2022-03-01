package com.example.core.product.domain.usecase

import com.example.core.common.State
import com.example.core.product.domain.entity.Product
import com.example.core.product.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(private val productsRepository: ProductsRepository) {

    fun invoke(): Flow<State<List<Product>>> {
        return productsRepository.getProducts()
    }
}