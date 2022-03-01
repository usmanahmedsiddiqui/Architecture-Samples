package com.example.core.cart.domain.usecase

import app.cash.turbine.test
import com.example.core.cart.domain.repository.FakeCartRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CalculateTotalUseCaseTest {

    private lateinit var fakeCartRepository: FakeCartRepository
    private lateinit var calculateTotalUseCase: CalculateTotalUseCase

    @Before
    fun setup() {
        fakeCartRepository = FakeCartRepository()
        calculateTotalUseCase = CalculateTotalUseCase(fakeCartRepository)
    }


    @Test
    fun `valid total`() = runBlocking {

        val flow = calculateTotalUseCase.invoke()

        flow.test {
            val total = awaitItem()
            Truth.assertThat(total.amount == 4.0).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `invalid total`() = runBlocking {

        val flow = calculateTotalUseCase.invoke()

        flow.test {
            val total = awaitItem()
            Truth.assertThat(total.amount == 2.0).isFalse()
            awaitComplete()
        }
    }
}