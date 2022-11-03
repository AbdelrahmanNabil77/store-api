package com.example.storeapi.datalayer.remote

import com.example.storeapi.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}