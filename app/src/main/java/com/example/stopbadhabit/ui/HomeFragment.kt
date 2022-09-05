package com.example.stopbadhabit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentHomeBinding
import com.example.stopbadhabit.ui.adapter.HomeHabitListAdapter
import com.example.stopbadhabit.ui.viewmodel.BottomSheetViewModel
import com.example.stopbadhabit.ui.viewmodel.HomeViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val homeViewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var homeHabitListAdapter: HomeHabitListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setView()
    }

    override fun onResume() {
        super.onResume()
//        homeViewModel.getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setView() {
        homeHabitListAdapter = HomeHabitListAdapter({
            Log.e(javaClass.simpleName, "setView: ${it}")
            mainViewModel.detailHabitId = it
//            mainViewModel.getDiaryList()
            findNavController().navigate(R.id.action_HomeFragment_to_habitDetailFragment)
        }, {
            findNavController().navigate(R.id.action_homeFragment_to_diaryWriteFragment)
        }).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvHomeHabit.adapter = homeHabitListAdapter // 리사이클러 뷰 연결
    }

    private fun setObserver() {
        mainViewModel.habitList.observe(viewLifecycleOwner) {
            Log.e(javaClass.simpleName, "setObserver: ${it}")
            homeHabitListAdapter.setData(it)
            if (it.size == 3)
                binding.ivHabitAdd.visibility = View.GONE
            else
                binding.ivHabitAdd.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.ivHistory.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_historyFragment)

        }

        binding.tvTitle.setOnLongClickListener { it ->
            findNavController().navigate(R.id.action_HomeFragment_to_openSourceFragment)
            true
        }

        binding.ivHabitAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetFragment)
        }

        binding.btnBoom.setOnClickListener {
            mainViewModel.deleteAll()
        }

        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle?): HomeFragment {
            val fragment = HomeFragment()
            bundle?.let {
                fragment.arguments = it
            }
            return fragment
        }
    }

}