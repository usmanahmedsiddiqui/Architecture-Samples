package com.example.architecturesamples.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturesamples.common.util.replace
import com.example.architecturesamples.product.event.ProductEvent
import com.example.core.cart.domain.entity.ProductDto
import com.example.core.cart.domain.usecase.CartUseCases
import com.example.core.common.State
import com.example.core.product.domain.entity.Product
import com.example.core.product.domain.usecase.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val products =
        MutableStateFlow<State<List<Product>>>(State.loading())

    private val mappedProducts =
        MutableStateFlow(emptyList<ProductDto>())

    init {
        onEvent(ProductEvent.GetProducts)
    }

    fun onEvent(productEvent: ProductEvent) {
        when (productEvent) {
            ProductEvent.GetProducts -> {
                viewModelScope.launch {
                    productUseCases.getProductUseCase.invoke()
                        .collectLatest {
                            products.value = it
                        }
                }
            }

            is ProductEvent.MapProducts -> {
                viewModelScope.launch {
                    productUseCases.mapProductUseCase
                        .invoke(productEvent.list)
                        .collectLatest {
                            mappedProducts.value = it
                        }
                }
            }

            is ProductEvent.UpdateProductCount -> {
                viewModelScope.launch {
                    cartUseCases.addOrUpdateItemUseCase
                        .invoke(productEvent.productDto)
                        .collectLatest {
                            mappedProducts.value =
                                mappedProducts.value.replace(productEvent.productDto) {
                                    it.id == productEvent.productDto.id
                                }
                        }
                }
            }
        }
    }

    fun products(): StateFlow<State<List<Product>>> = products

    fun mappedProducts(): Flow<List<ProductDto>> = mappedProducts

}