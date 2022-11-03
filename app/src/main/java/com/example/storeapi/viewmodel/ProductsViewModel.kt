package com.example.storeapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.storeapi.R
import com.example.storeapi.datalayer.repository.ProductsRepository
import com.example.storeapi.model.Product
import com.example.storeapi.utils.AppUtils.checkForInternet
import com.example.storeapi.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class ProductsViewModel(
    val repository: ProductsRepository,
    val app: Application
) : AndroidViewModel(app) {
    private val _productsLiveData: MutableLiveData<Resource<List<Product>>> = MutableLiveData()
    val productsLiveData: LiveData<Resource<List<Product>>>
        get() = _productsLiveData

    fun getProductsList() = viewModelScope.launch {
        try {
            if (checkForInternet(app)) {
                _productsLiveData.postValue(Resource.Loading())
                val response = repository.getProducts()
                response.body()?.let {
                    _productsLiveData.postValue(Resource.Success(it))
                }
            } else {
                _productsLiveData.postValue(Resource.Error(app.getString(R.string.no_internet_error)))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _productsLiveData.postValue(Resource.Error(app.getString(R.string.network_failure)))
                else -> _productsLiveData.postValue(Resource.Error("${app.getString(R.string.error_msg)} ${t.message}"))
            }
        }
    }

}