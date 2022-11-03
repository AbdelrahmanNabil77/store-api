package com.example.storeapi.view.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.storeapi.R
import com.example.storeapi.databinding.FragmentHomeBinding
import com.example.storeapi.datalayer.repository.ProductsRepository
import com.example.storeapi.utils.Resource
import com.example.storeapi.viewmodel.ProductsViewModel
import com.example.storeapi.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: ProductsViewModel
    lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as Application
        val productsRepository = ProductsRepository()
        val viewModelProviderFactory =
            ViewModelFactory(productsRepository, application)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(ProductsViewModel::class.java)
        setObservers()
        setListeners()
        viewModel.getProductsList()
    }

    fun setObservers(){
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.tvError.hide()
                    binding.ivError.hide()
                    binding.recyclerViewHome.show()
                    it.data?.let {
                        homeRecyclerViewAdapter = HomeRecyclerViewAdapter(it,
                            HomeRecyclerViewAdapter.ProductClickListener {
                                val action =
                                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                                findNavController().navigate(action)
                            })
                        binding.recyclerViewHome.adapter=homeRecyclerViewAdapter
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.ivError.show()
                    binding.tvError.text=it.message
                    binding.tvError.show()
                    binding.recyclerViewHome.hide()
                }
                is Resource.Loading -> {
                    binding.tvError.hide()
                    binding.ivError.hide()
                    binding.progressBar.show()
                }
            }
        }
    }

    fun setListeners(){
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing=false
            viewModel.getProductsList()
        }
    }

    fun View.show(){
        this.visibility=View.VISIBLE
    }
    fun View.hide(){
        this.visibility=View.GONE
    }
}