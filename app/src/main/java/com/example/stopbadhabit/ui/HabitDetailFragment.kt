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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary

import com.example.stopbadhabit.databinding.FragmentHabitDetailBinding
import com.example.stopbadhabit.ui.adapter.DiaryListAdapter
import com.example.stopbadhabit.ui.adapter.HomeHabitListAdapter
import com.example.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.toDate
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.compose.viewModel
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HabitDetailFragment : Fragment() {

    private val habitDetailViewModel : HabitDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val binding by lazy { FragmentHabitDetailBinding.inflate(layoutInflater) }

    private lateinit var diaryListAdapter: DiaryListAdapter


    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    private fun setView() {
        diaryListAdapter = DiaryListAdapter {
            findNavController().navigate(R.id.action_habitDetailFragment_to_diaryDetailFragment)
        }.apply {
//            setHasStableIds(true)
        }
        binding.rvDiary.adapter = diaryListAdapter
        binding.rvDiary.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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

        setView()

        binding.ivDiaryAdd.setOnClickListener {
            findNavController().navigate(R.id.action_habitDetailFragment_to_diaryWriteFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        habitDetailViewModel.habit.observe(viewLifecycleOwner){

            it.habit_id?.let { id ->
                habitDetailViewModel.getDiary(id)
            }

            with(binding){
                Log.e(javaClass.simpleName, "habit_id: ${it.habit_id}", )
                tvHdName.text = it.name
                tvHdStartDate.text = it.start_date
                tvHdCurrentdaystate.text = String.format(requireContext().getString(R.string.hd_current),(today - it.start_date.toDate()) / (24 * 60 * 60 * 1000)+1)
                //tvHdLastDiaryDate
                tvHdLastDiaryDate.text="테스트중임다"
                //TODO 0번일 때 멘트 생각
                tvHdFailDefend.text = String.format(requireContext().getString(R.string.hd_fail),it.setting_life-it.current_life)
                tvHdLife.text = String.format(requireContext().getString(R.string.life),it.current_life,it.setting_life)

            }
        }
    }

    private fun setObserver(){
//        mainViewModel.diaryList.observe(viewLifecycleOwner) {
//            Log.e(javaClass.simpleName, "setObserver: $it" )
//            diaryListAdapter.list = it
//        }

        habitDetailViewModel.diaryList.observe(viewLifecycleOwner) {
            diaryListAdapter.list = it as ArrayList<Diary> /* = java.util.ArrayList<com.example.stopbadhabit.data.model.Diary.Diary> */
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