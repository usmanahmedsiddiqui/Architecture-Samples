package com.example.core.product.domain.repository

import com.example.core.common.State
import com.example.core.product.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeProductRepository : ProductsRepository {

    private var shouldThrowError: Boolean = false

    fun setShouldThrowError(value: Boolean) {
        shouldThrowError = value
    }

    override fun getProducts(): Flow<State<List<Product>>> {
        return if (shouldThrowError)
            emitErrorState()
        else emitSuccessState()
    }


    private fun emitSuccessState(): Flow<State<List<Product>>> =
        flow {
            emit(State.loading())
            emit(State.success(emptyList()))

        }

    private fun emitErrorState(): Flow<State<List<Product>>> =
        flow {
            emit(State.loading())
            emit(State.error(IOException()))
        }
}