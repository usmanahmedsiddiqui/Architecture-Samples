package com.example.core.product.domain.repository

import com.example.core.common.State
import com.example.core.common.data.remote.ApiService
import com.example.core.product.domain.entity.Product
import com.example.core.product.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

internal class ProductsRepositoryImpl(
    private val service: ApiService,
    private val dispatcher: CoroutineDispatcher
) : ProductsRepository {
    override fun getProducts(): Flow<State<List<Product>>> = flow {
        emit(service.getProducts())
    }.map {
        State.success(it)
    }.onStart {
        emit(State.loading())
    }.catch {
        emit(State.error(it))
    }.flowOn(dispatcher)

}