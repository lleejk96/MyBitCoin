package com.example.coin.view.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.coin.R
import com.example.coin.databinding.FragmentIntro1Binding

class IntroFragment1 : Fragment() {

    private var _binding : FragmentIntro1Binding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.nextBtn?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_introFragment1_to_introFragment2)
        }
    }
}