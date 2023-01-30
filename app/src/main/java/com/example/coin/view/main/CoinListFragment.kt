package com.example.coin.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coin.database.enity.InterestCoinPriceEntity
import com.example.coin.databinding.FragmentCoinListBinding
import com.example.coin.databinding.FragmentIntro1Binding
import com.example.coin.view.adapter.CoinListRVAdapter
import com.example.coin.viewmodel.MainViewModel

class CoinListFragment : Fragment() {
    private lateinit var _binding : FragmentCoinListBinding
    private val viewModel : MainViewModel by activityViewModels()

    private val selectedList = ArrayList<InterestCoinPriceEntity>()
    private val unSelectedList = ArrayList<InterestCoinPriceEntity>()

    private lateinit var selectedRVAdapter : CoinListRVAdapter
    private lateinit var unSelectedRVAdapter : CoinListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestCoinData()
        viewModel.selectedCoinList.observe(viewLifecycleOwner, Observer {
            selectedList.clear()
            unSelectedList.clear()
            for (item in it){
                if (item.selected)
                    selectedList.add(item)
                else
                    unSelectedList.add(item)
            }
            setListView()
        })
    }

    private fun setListView(){
        selectedRVAdapter = CoinListRVAdapter(requireContext(), selectedList)
        _binding.selectedCoinRV.adapter = selectedRVAdapter
        _binding.selectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        selectedRVAdapter.itemClick = object : CoinListRVAdapter.onClickItem {
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoinData(selectedList[position])
            }

        }

        unSelectedRVAdapter = CoinListRVAdapter(requireContext(), unSelectedList)
        _binding.unSelectedCoinRV.adapter = unSelectedRVAdapter
        _binding.unSelectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        unSelectedRVAdapter.itemClick = object : CoinListRVAdapter.onClickItem{
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoinData(unSelectedList[position])
            }

        }
    }
}