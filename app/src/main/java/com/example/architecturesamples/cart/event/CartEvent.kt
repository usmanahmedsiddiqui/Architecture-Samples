package com.example.architecturesamples.cart.event

import com.example.core.cart.domain.entity.ProductDto


sealed class CartEvent {

    object GetCart : CartEvent()

    data class UpdateProductCount(val productDto: ProductDto) :
        CartEvent()

    object CalculateTotal : CartEvent()

}