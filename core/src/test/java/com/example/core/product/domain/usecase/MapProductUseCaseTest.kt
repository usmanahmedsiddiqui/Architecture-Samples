package com.example.core.product.domain.usecase

import app.cash.turbine.test
import com.example.core.cart.domain.repository.FakeCartRepository
import com.example.core.common.util.TestProductProvider
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MapProductUseCaseTest {

    private lateinit var fakeCartRepository: FakeCartRepository
    private lateinit var mapProductUseCase: MapProductUseCase

    @Before
    fun setup() {
        fakeCartRepository = FakeCartRepository()
        mapProductUseCase = MapProductUseCase(fakeCartRepository)
    }


    @Test
    fun `response valid mapped list`() = runBlocking {

        val flow = mapProductUseCase.invoke(TestProductProvider.getProductList())

        flow.test {
            val list = awaitItem()
            Truth.assertThat(list.firstOrNull { it.id == "1" }?.count == 2).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `response invalid mapped list`() = runBlocking {

        val flow = mapProductUseCase.invoke(TestProductProvider.getProductList())

        flow.test {
            val list = awaitItem()
            Truth.assertThat(list.firstOrNull { it.id == "1" }?.count == 1).isFalse()
            awaitComplete()
        }
    }
}