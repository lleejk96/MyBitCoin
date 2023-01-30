package com.example.coin.view.intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.coin.R
import com.example.coin.databinding.FragmentIntro1Binding
import com.example.coin.databinding.FragmentIntro2Binding
import com.example.coin.view.SelectActivity

class IntroFragment2 : Fragment() {
    private var _binding : FragmentIntro2Binding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntro2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.nextBtn?.setOnClickListener {
            val intent = Intent(requireContext(), SelectActivity::class.java)
            startActivity(intent)
        }
    }
}