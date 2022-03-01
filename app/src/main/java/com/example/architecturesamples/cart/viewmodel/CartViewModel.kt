package com.example.architecturesamples.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturesamples.cart.event.CartEvent
import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.usecase.CartUseCases
import com.example.core.product.domain.entity.Price
import com.example.architecturesamples.common.util.replace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val cartList =
        MutableStateFlow(emptyList<ProductDto>())

    private val total =
        MutableStateFlow(Price(0.0, "EUR"))

    init {
        onEvent(CartEvent.GetCart)
    }

    fun onEvent(cartEvent: CartEvent) {
        when (cartEvent) {
            CartEvent.GetCart -> {
                viewModelScope.launch {
                    cartUseCases.getAllItemsUseCase.invoke()
                        .collectLatest {
                            cartList.value = it
                        }
                }
            }

            is CartEvent.UpdateProductCount -> {
                viewModelScope.launch {
                    cartUseCases.addOrUpdateItemUseCase
                        .invoke(cartEvent.productDto)
                        .collectLatest {
                            cartList.value =
                                cartList.value.replace(cartEvent.productDto) {
                                    it.id == cartEvent.productDto.id
                                }
                        }
                }
            }

            is CartEvent.CalculateTotal -> {
                viewModelScope.launch {
                    cartUseCases.calculateTotalUseCase
                        .invoke()
                        .collectLatest {
                            total.value = it
                        }
                }
            }
        }
    }


    fun cartList(): Flow<List<ProductDto>> = cartList

    fun total(): Flow<Price> = total
}