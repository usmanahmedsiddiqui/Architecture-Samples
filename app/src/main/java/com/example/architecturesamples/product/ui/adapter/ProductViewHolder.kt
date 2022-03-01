package com.example.architecturesamples.product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.architecturesamples.common.util.format
import com.example.architecturesamples.databinding.ViewholderProductBinding
import com.example.core.cart.domain.entity.ProductDto

class ProductViewHolder(
    parent: ViewGroup,
    private val binding: ViewholderProductBinding = ViewholderProductBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun render(product: ProductDto, onUpdate: (ProductDto) -> Unit) {
        with(binding) {
            imageView.load(product.thumbnail)
            labelTitle.text = product.title
            labelPrice.text = product.price.format()
            labelCount.text = product.count.toString()

            buttonMinus.setOnClickListener {
                if (product.count > 0) {
                    onUpdate(product.copy(count = product.count - 1))
                }
            }

            buttonPlus.setOnClickListener {
                onUpdate(product.copy(count = product.count + 1))
            }
        }
    }
}
