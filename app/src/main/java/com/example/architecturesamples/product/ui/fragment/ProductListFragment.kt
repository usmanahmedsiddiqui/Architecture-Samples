package com.example.architecturesamples.product.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturesamples.common.util.safeLaunchInFragment
import com.example.architecturesamples.common.util.viewBinding
import com.example.architecturesamples.databinding.FragmentProductListBinding
import com.example.architecturesamples.product.event.ProductEvent
import com.example.architecturesamples.product.ui.adapter.ProductAdapter
import com.example.core.common.State
import com.example.architecturesamples.product.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private var binding: FragmentProductListBinding by viewBinding()

    private val viewModel: ProductViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeData()
    }

    private fun observeData() {
        safeLaunchInFragment(viewModel.products()) { state ->
            when (state) {
                is State.Loading -> {
                    binding.loadingBar.isVisible = true
                }

                is State.Error -> {
                    binding.loadingBar.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.throwable.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is State.Success -> {
                    binding.loadingBar.isVisible = false
                    viewModel.onEvent(ProductEvent.MapProducts(state.data))
                }
            }
        }

        safeLaunchInFragment(viewModel.mappedProducts()) {
            productAdapter.submitList(it)
        }
    }

    private fun initAdapter() {
        binding.apply {
            productAdapter = ProductAdapter { productDto ->
                viewModel.onEvent(
                    ProductEvent.UpdateProductCount(
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