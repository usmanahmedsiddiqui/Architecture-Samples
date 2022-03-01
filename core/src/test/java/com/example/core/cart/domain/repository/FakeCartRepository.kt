package com.example.core.cart.domain.repository

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.common.util.TestProductProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCartRepository : CartRepository {
    override suspend fun addItem(productDto: ProductDto): Flow<Unit> {
        return flow { emit(Unit) }
    }

    override suspend fun updateItem(productId: String, count: Int): Flow<Unit> {
        return flow { emit(Unit) }
    }

    override suspend fun deleteItem(productDto: ProductDto): Flow<Unit> {
        return flow { emit(Unit) }
    }

    override fun getAllProducts(): Flow<List<ProductDto>> {
        return flow {
            emit(TestProductProvider.getProductDtoList())
        }
    }

    override fun getSingleProduct(productId: String): Flow<ProductDto?> {
        return flow { emit(TestProductProvider.getSingleProductDto()) }
    }
}