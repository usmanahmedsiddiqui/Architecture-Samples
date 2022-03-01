package com.example.core.cart.di

import com.example.core.cart.domain.repository.CartRepository
import com.example.core.cart.domain.repository.CartRepositoryImpl
import com.example.core.cart.domain.usecase.*
import com.example.core.common.data.local.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CartModule {

    @Singleton
    @Provides
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }

    @Singleton
    @Provides
    fun provideCartUseCases(cartRepository: CartRepository): CartUseCases {
        return CartUseCases(
            AddOrUpdateItemUseCase(cartRepository),
            DeleteItemUseCase(cartRepository),
            GetAllItemsUseCase(cartRepository),
            GetSingleItemUseCase(cartRepository),
            CalculateTotalUseCase(cartRepository)
        )
    }
}