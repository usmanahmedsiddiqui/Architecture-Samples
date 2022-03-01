package com.example.architecturesamples.product.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.cart.domain.entity.ProductDto

class ProductAdapter(private val onUpdate: (ProductDto) -> Unit) :
    ListAdapter<ProductDto, ProductViewHolder>(DiffUtilCallback()) {

    private class DiffUtilCallback : DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.render(currentList[position], onUpdate)

}