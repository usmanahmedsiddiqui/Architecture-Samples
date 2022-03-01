package com.example.core.cart.domain.usecase

data class CartUseCases(
    val addOrUpdateItemUseCase: AddOrUpdateItemUseCase,
    val deleteItemUseCase: DeleteItemUseCase,
    val getAllItemsUseCase: GetAllItemsUseCase,
    val getSingleItemUseCase: GetSingleItemUseCase,
    val calculateTotalUseCase: CalculateTotalUseCase
)