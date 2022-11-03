package com.example.storeapi.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapi.databinding.ItemHomeRecyclerBinding
import com.example.storeapi.model.Product

class HomeRecyclerViewAdapter(
    val products:List<Product>,
    val clickListener: ProductClickListener
) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.ItemProductViewHolder>() {
    class ItemProductViewHolder(val binding: ItemHomeRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductViewHolder {
        return ItemProductViewHolder(
            ItemHomeRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemProductViewHolder, position: Int) {
        holder.binding.product=products.get(position)
        holder.binding.productClickListener=clickListener
    }

    override fun getItemCount(): Int {
        return products.size
    }
    class ProductClickListener(val clickListener: (product:Product) -> Unit) {
        fun onClick(product:Product) = clickListener(product)
    }
}