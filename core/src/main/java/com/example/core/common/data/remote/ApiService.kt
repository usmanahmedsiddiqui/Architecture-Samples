package com.example.core.common.data.remote

import com.example.core.product.domain.entity.Product
import retrofit2.http.GET

interface ApiService {

  @GET("/v3/541cf783-5f48-43ca-ac10-c9e90ed84207")
  suspend fun getProducts(): List<Product>
}