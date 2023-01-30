package com.example.coin.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coin.databinding.FragmentCoinListBinding
import com.example.coin.databinding.FragmentPriceChangeBinding

class PriceChangeFragment : Fragment() {
    private lateinit var _binding : FragmentPriceChangeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPriceChangeBinding.inflate(inflater, container, false)
        return binding.root
    }
}