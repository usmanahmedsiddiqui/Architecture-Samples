package com.example.architecturesamples.product.event

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.product.domain.entity.Product


sealed class ProductEvent {

    object GetProducts : ProductEvent()

    data class MapProducts(val list: List<Product>) : ProductEvent()

    data class UpdateProductCount(val productDto: ProductDto) :
        ProductEvent()

}