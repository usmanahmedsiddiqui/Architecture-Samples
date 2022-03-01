package com.example.core.product.di

import com.example.core.cart.domain.repository.CartRepository
import com.example.core.common.data.remote.ApiService
import com.example.core.product.domain.repository.ProductsRepository
import com.example.core.product.domain.repository.ProductsRepositoryImpl
import com.example.core.product.domain.usecase.GetProductUseCase
import com.example.core.product.domain.usecase.MapProductUseCase
import com.example.core.product.domain.usecase.ProductUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        apiService: ApiService,
        dispatcher: CoroutineDispatcher,
    ): ProductsRepository {
        return ProductsRepositoryImpl(apiService, dispatcher)
    }

    @Singleton
    @Provides
    fun provideProductUseCases(
        productsRepository: ProductsRepository,
        cartRepository: CartRepository
    ) = ProductUseCases(
        GetProductUseCase(productsRepository),
        MapProductUseCase(cartRepository)
    )
}