package com.example.core.common.data.local

import androidx.room.*
import com.example.core.cart.domain.entity.ProductDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(product: ProductDto)

    @Query("UPDATE cart SET count =:count WHERE id =:productId")
    suspend fun updateItem(productId: String, count: Int)

    @Delete
    suspend fun deleteItem(product: ProductDto)

    @Query("SELECT * FROM cart WHERE id=:productId")
    fun getSingleProduct(productId: String): Flow<ProductDto?>

    @Query("SELECT * FROM cart")
    fun getAllProducts(): Flow<List<ProductDto>>
}