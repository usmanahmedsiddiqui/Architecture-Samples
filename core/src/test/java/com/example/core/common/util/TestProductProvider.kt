package com.example.core.common.util

import com.example.core.cart.domain.entity.ProductDto
import com.example.core.product.domain.entity.Price
import com.example.core.product.domain.entity.Product

object TestProductProvider {

    fun getSingleProductDto(): ProductDto {
        return ProductDto(
            "1",
            "Product 1",
            "random string",
            Price(2.0, "EUR"),
            1
        )
    }

    fun getProductDtoList(): List<ProductDto> {
        val list = mutableListOf<ProductDto>()

        list.add(
            ProductDto(
                "1",
                "Product 1",
                "random string",
                Price(2.0, "EUR"),
                2
            )
        )
        return list
    }

    fun getProductList(): List<Product> {
        val list = mutableListOf<Product>()

        list.add(
            Product(
                "1",
                "Product 1",
                "random string",
                Price(1.0, "EUR")
            )
        )

        list.add(
            Product(
                "2",
                "Product 2",
                "random string",
                Price(2.0, "EUR")
            )
        )

        return list
    }
}