package com.example.stopbadhabit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentHistoryBinding
import com.example.stopbadhabit.databinding.FragmentHomeBinding
import com.example.stopbadhabit.ui.adapter.HistoryHabitListAdapter
//import com.example.stopbadhabit.ui.adapter.HistoryHabitListAdapter
import com.example.stopbadhabit.ui.adapter.HomeHabitListAdapter
import com.example.stopbadhabit.ui.viewmodel.HabitHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private val binding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }
    private val habitHistoryViewModel: HabitHistoryViewModel by viewModels()
    private lateinit var historyHabitListAdapter: HistoryHabitListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnHhBack.setOnClickListener {
            findNavController().popBackStack()
        }
        setObserver()
    }

    private fun setView() {
        historyHabitListAdapter = HistoryHabitListAdapter {
            val action =
                HistoryFragmentDirections.actionHistoryFragmentToDiaryDetailFragment(it)
            findNavController().navigate(action)
        }.apply {
            setHasStableIds(true)
        }
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        manager.reverseLayout = true
        manager.stackFromEnd = true
        binding.rvHistoryHabit.adapter = historyHabitListAdapter
        binding.rvHistoryHabit.layoutManager = manager

    }

    private fun setObserver() {
        habitHistoryViewModel.habitAndDiaryList.observe(viewLifecycleOwner) {
            historyHabitListAdapter.setData(it)
            if (it.size == 0)
                binding.tvEmpty.visibility = View.VISIBLE
            else
                binding.tvEmpty.visibility = View.GONE
        }
    }
}