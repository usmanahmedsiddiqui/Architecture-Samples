package com.example.architecturesamples.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturesamples.cart.event.CartEvent
import com.example.architecturesamples.databinding.FragmentCartBinding
import com.example.architecturesamples.cart.viewmodel.CartViewModel
import com.example.architecturesamples.common.util.format
import com.example.architecturesamples.common.util.safeLaunchInFragment
import com.example.architecturesamples.common.util.viewBinding
import com.example.architecturesamples.product.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var binding: FragmentCartBinding by viewBinding()

    private val viewModel: CartViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeData()
    }

    private fun observeData() {
        safeLaunchInFragment(viewModel.cartList()) {
            productAdapter.submitList(it)
            binding.emptyView.root.isVisible = it.isEmpty()
            binding.cartView.root.isVisible = it.isNotEmpty()
            viewModel.onEvent(CartEvent.CalculateTotal)
        }

        safeLaunchInFragment(viewModel.total()) {
            binding.cartView.labelPrice.text = it.format()
        }
    }

    private fun initAdapter() {
        binding.apply {
            productAdapter = ProductAdapter { productDto ->
                viewModel.onEvent(
                    CartEvent.UpdateProductCount(
                        productDto,
                    )
                )
            }
            val linearLayoutManager = LinearLayoutManager(requireContext())

            with(recyclerview) {
                itemAnimator = null
                layoutManager = linearLayoutManager
                adapter = productAdapter

            }
        }
    }


}