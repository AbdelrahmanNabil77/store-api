package com.example.storeapi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storeapi.datalayer.repository.ProductsRepository

class ViewModelFactory(
    val productsRepository: ProductsRepository,
    val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(productsRepository,application) as T
        }
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}