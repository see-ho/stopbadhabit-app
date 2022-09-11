package com.example.stopbadhabit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary

import com.example.stopbadhabit.databinding.FragmentHabitDetailBinding
import com.example.stopbadhabit.ui.adapter.DiaryListAdapter
import com.example.stopbadhabit.ui.adapter.HomeHabitListAdapter
import com.example.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.toDate
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
            val action = HabitDetailFragmentDirections.actionHabitDetailFragmentToDiaryDetailFragment(it)
            findNavController().navigate(action)
        }.apply {
            setHasStableIds(true)
        }
        binding.rvDiary.adapter = diaryListAdapter
        binding.rvDiary.layoutManager =
            GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setView()
        binding.ivDiaryAdd.setOnClickListener {
            findNavController().navigate(R.id.action_habitDetailFragment_to_diaryWriteFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()

        val balloon = Balloon.Builder(requireContext())
            .setWidthRatio(0.65f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setMargin(5)
            .setLayout(R.layout.tooltip)
            .setCornerRadius(8f)
            .setArrowColorMatchBalloon(true)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setBackgroundColorResource(R.color.white)
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

        binding.btnTooltip.setOnClickListener {
            balloon.showAlignBottom(binding.btnTooltip )
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_habitDetailFragment_to_homeFragment)
        }

        mainViewModel.detailHabitId.observe(viewLifecycleOwner) {
            if(it != -1)
                habitDetailViewModel.getHabitDetail(it)
            else
                //TODO 예외처리
                Log.e("fsda", "onCreateView: 헤빗아이디에러", )
        }

        mainViewModel.diaryList.observe(viewLifecycleOwner) { it ->
            if (it.isNotEmpty())
                when(((today - it.last().diary_date.toDate()) / (24 * 60 * 60 * 1000)).toInt()){
                    0-> binding.tvHdLastDiaryDate.text = String.format(
                        requireContext().getString(
                            R.string.hd_continuity_0,
                        ))
                    else ->binding.tvHdLastDiaryDate.text = String.format(
                        requireContext().getString(
                            R.string.hd_continuity,((today - it.last().diary_date.toDate()) / (24 * 60 * 60 * 1000) -1)
                        ))
                }
            else
                binding.tvHdLastDiaryDate.text = String.format(
                    requireContext().getString(
                        R.string.hd_never_failed,
                    ))
        }

        habitDetailViewModel.habit.observe(viewLifecycleOwner){

            if(it.current_life == 0)
                findNavController().navigate(R.id.action_habitDetailFragment_to_homeFragment)

            it.habit_id?.let { id ->
                mainViewModel.getDiaryList(id)
            }

            with(binding){
                Log.e(javaClass.simpleName, "habit_id: ${it.habit_id}", )
                when(it.mode){
                    0-> Glide.with(binding.root).load(R.drawable.bg_mob_easy).into(binding.ivHdMob)
                    1-> Glide.with(binding.root).load(R.drawable.bg_mob_normal).into(binding.ivHdMob)
                    2-> Glide.with(binding.root).load(R.drawable.bg_mob_hard).into(binding.ivHdMob)
                }
                tvHdName.text = it.name
                tvHdStartDate.text = "~${it.start_date}"
                when(habitDetailViewModel.setFromStartDate().toInt()){
                    -1 -> Log.e(javaClass.simpleName, "onViewCreated: 수고", )
                    else -> tvHdCurrentdaystate.text = String.format(requireContext().getString(R.string.hd_current),habitDetailViewModel.setFromStartDate())

                }
                when(it.setting_life-it.current_life){
                    0-> tvHdFailDefend.text = String.format(requireContext().getString(R.string.hd_fail_0))
                    else -> tvHdFailDefend.text = String.format(requireContext().getString(R.string.hd_fail),it.setting_life-it.current_life)
                }
                tvHdLife.text = String.format(requireContext().getString(R.string.life),it.current_life,it.setting_life)

            }
        }


    }


    private fun setObserver(){
        mainViewModel.diaryList.observe(viewLifecycleOwner) {
            diaryListAdapter.setData(it)
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