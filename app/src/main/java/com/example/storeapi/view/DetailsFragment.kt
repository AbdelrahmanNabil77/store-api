package com.example.storeapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.storeapi.R
import com.example.storeapi.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        return binding.root
    }
}