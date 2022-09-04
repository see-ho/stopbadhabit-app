package com.example.stopbadhabit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stopbadhabit.R

import com.example.stopbadhabit.databinding.FragmentHabitDetailBinding
import com.example.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.toDate
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.compose.viewModel
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class HabitDetailFragment : Fragment() {

    private val habitDetailViewModel : HabitDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val diaryBottomSheetFragment : DiaryWriteFragment by lazy {
        DiaryWriteFragment.newInstance()
    }
    private val binding by lazy { FragmentHabitDetailBinding.inflate(layoutInflater) }
    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(mainViewModel.detailHabitId != -1)
            habitDetailViewModel.getHabitDetail(mainViewModel.detailHabitId)
        else
            Log.e("fsda", "onCreateView: sdfsdf", )
            //TODO 에러처리

        binding.ivDiaryAdd.setOnClickListener {
            findNavController().navigate(R.id.action_habitDetailFragment_to_diaryWriteFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        habitDetailViewModel.habit.observe(viewLifecycleOwner){
            with(binding){
                tvHdName.text = it.name
                tvHdStartDate.text = it.start_date
                Log.e(javaClass.simpleName, "${it.start_date.toDate()} / ${today}", )
                tvHdCurrentdaystate.text = String.format(requireContext().getString(R.string.hd_current),(today - it.start_date.toDate()) / (24 * 60 * 60 * 1000)+1)
                //tvHdLastDiaryDate
                //TODO 0번일 때 멘트 생각
                tvHdFailDefend.text = String.format(requireContext().getString(R.string.hd_fail),it.setting_life-it.current_life)
                tvHdLife.text = String.format(requireContext().getString(R.string.life),it.current_life,it.setting_life)

            }
        }
    }



    companion object {
        fun newInstance(bundle: Bundle?): HabitDetailFragment {
            val fragment = HabitDetailFragment()
            bundle?.let {
                fragment.arguments = it
            }
            return fragment
        }
    }
}