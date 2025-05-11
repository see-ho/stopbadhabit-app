package com.seeho.stopbadhabit.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.databinding.FragmentHomeBinding
import com.seeho.stopbadhabit.ui.adapter.HomeHabitListAdapter
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var homeHabitListAdapter: HomeHabitListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setView() {
            homeHabitListAdapter = HomeHabitListAdapter(
            {
                mainViewModel.setDetailId(it)
                HabitReportFragment{
                    mainViewModel.habitList.value?.clear()
                    mainViewModel.getHabitList()
                }.apply {
                    show(this@HomeFragment.childFragmentManager, "habitReport")
                }
            },
            {
                mainViewModel.setDetailId(it)
                findNavController().navigate(R.id.action_HomeFragment_to_habitDetailFragment)
            },
            {
                mainViewModel.setDetailId(it)
//                DiaryWriteFragment{}.apply {
//                    show(this@HomeFragment.childFragmentManager, "diaryWrite")
//                }
            },
        ).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvHomeHabit.adapter = homeHabitListAdapter // 리사이클러 뷰 연결
    }

    private fun setObserver() {
        mainViewModel.habitList.observe(viewLifecycleOwner) {
            homeHabitListAdapter.setData(it)
            if (it.size == 3)
                binding.ivHabitAdd.root.visibility = View.GONE
            else
                binding.ivHabitAdd.root.visibility = View.VISIBLE
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
            Intent(requireActivity().applicationContext, OssLicensesMenuActivity::class.java).also {
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
                startActivity(it)
            }
            true
        }

        binding.ivHabitAdd.root.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetFragment)
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