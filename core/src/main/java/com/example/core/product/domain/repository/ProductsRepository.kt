package com.example.core.product.domain.repository

import com.example.core.common.State
import com.example.core.product.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
   fun getProducts(): Flow<State<List<Product>>>
}