package com.example.core.product.domain.usecase

import app.cash.turbine.test
import com.example.core.common.State
import com.example.core.product.domain.repository.FakeProductRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProductUseCaseTest {

    private lateinit var fakeProductRepository: FakeProductRepository
    private lateinit var getProductUseCases: GetProductUseCase

    @Before
    fun setup() {
        fakeProductRepository = FakeProductRepository()
        getProductUseCases = GetProductUseCase(fakeProductRepository)
    }

    @Test
    fun `response emit loading and error when server throws error`() = runBlocking {
        fakeProductRepository.setShouldThrowError(true)

        val flow = getProductUseCases.invoke()

        flow.test {
            assertThat(awaitItem() is State.Loading).isTrue()
            assertThat(awaitItem() is State.Error).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `response emit loading and success when server emit success`() = runBlocking {
        fakeProductRepository.setShouldThrowError(false)

        val flow = getProductUseCases.invoke()

        flow.test {
            assertThat(awaitItem() is State.Loading).isTrue()
            assertThat(awaitItem() is State.Success).isTrue()
            awaitComplete()
        }
    }
}