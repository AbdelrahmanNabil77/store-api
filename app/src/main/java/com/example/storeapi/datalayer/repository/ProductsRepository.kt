package com.example.storeapi.datalayer.repository

import com.example.storeapi.datalayer.remote.RetrofitInstance
import retrofit2.Retrofit

class ProductsRepository {
    suspend fun getProducts() = RetrofitInstance.api.getProducts()
}