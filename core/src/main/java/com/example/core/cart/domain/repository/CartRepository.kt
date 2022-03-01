package com.example.core.cart.domain.repository

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.common.data.local.CartDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CartRepository {

    suspend fun addItem(productDto: ProductDto): Flow<Unit>

    suspend fun updateItem(productId: String, count: Int): Flow<Unit>

    suspend fun deleteItem(productDto: ProductDto): Flow<Unit>

    fun getAllProducts(): Flow<List<ProductDto>>

    fun getSingleProduct(productId: String): Flow<ProductDto?>
}

class CartRepositoryImpl(private val cartDao: CartDao) : CartRepository {

    override suspend fun addItem(productDto: ProductDto): Flow<Unit> {
        return flow { emit(cartDao.addItem(productDto))   }
    }

    override suspend fun updateItem(productId: String, count: Int): Flow<Unit> {
        return flow { emit(cartDao.updateItem(productId, count)) }
    }

    override suspend fun deleteItem(productDto: ProductDto): Flow<Unit> {
        return flow {
            emit(cartDao.deleteItem(productDto))
        }
    }

    override fun getAllProducts(): Flow<List<ProductDto>> {
        return cartDao.getAllProducts()
    }

    override fun getSingleProduct(productId: String): Flow<ProductDto?> {
        return cartDao.getSingleProduct(productId)
    }

}