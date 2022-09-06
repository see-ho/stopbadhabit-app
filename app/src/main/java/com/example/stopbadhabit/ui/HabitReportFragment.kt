package com.example.stopbadhabit.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentHabitDetailBinding
import com.example.stopbadhabit.databinding.FragmentHabitReportBinding
import com.example.stopbadhabit.ui.viewmodel.HabitReportViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.Utils
import com.example.stopbadhabit.util.toCalender
import com.example.stopbadhabit.util.toDate
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class HabitReportFragment : DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val habitReportViewModel : HabitReportViewModel by viewModels()
    private val binding by lazy { FragmentHabitReportBinding.inflate(layoutInflater) }

    private var endDate : String = ""

    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time


    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded) {
            val fullWidth = Utils.getScreenWidth(requireActivity()) * .85
            dialog?.window?.setLayout(fullWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReportClose.setOnClickListener {
            dismiss()
        }

        mainViewModel.detailHabitId.observe(viewLifecycleOwner) {
            if(it != -1)
                habitReportViewModel.getHabitDetail(it)
            else
                Log.e("fsda", "onCreateView: sdfsdf", )
        }

        mainViewModel.diaryList.observe(viewLifecycleOwner) { it ->
            if (it.isNotEmpty())
                endDate = it.last().diary_date

        }

        habitReportViewModel.habit.observe(viewLifecycleOwner){
            with(binding){
                Log.e(javaClass.simpleName, "onViewCreated: $it", )
                val mDate = LocalDate.now()
                tvReportName.text=it.name

                tvReportLife.text = String.format(requireContext().getString(R.string.life),it.current_life,it.setting_life)

                Log.e(javaClass.simpleName, "onViewCreated: ${it.start_date.toDate()+it.goal_date}" )
                //TODO 여기 end_date
                if(it.current_life==0){
                    tvReportDate.text= String.format(requireContext().getString(R.string.hr_date),it.start_date,it.start_date.toCalender(it.goal_date))
                    tvState.text= String.format(requireContext().getString(R.string.hr_state_fail))
                    tvReportResult.text= String.format(requireContext().getString(R.string.hr_result_fail))
                    tvReportFromStart.text=String.format(requireContext().getString(R.string.hr_fromStart),15)
                    Glide.with(binding.root).load(R.drawable.bg_mob_hard).into(binding.ivReportChar)
                    Glide.with(binding.root).load(R.drawable.ic_emptyheart).into(binding.ivReportHeart)
                }
                else{
                    //tvReportDate.text= String.format(requireContext().getString(R.string.hr_date),)
                    tvState.text= String.format(requireContext().getString(R.string.hr_state_success))
                    tvReportResult.text= String.format(requireContext().getString(R.string.hr_result_success))
                    tvReportFromStart.text=String.format(requireContext().getString(R.string.hr_fromStart),15)
                    Glide.with(binding.root).load(R.drawable.bg_mob_hard).into(binding.ivReportChar)
                    Glide.with(binding.root).load(R.drawable.ic_heart).into(binding.ivReportHeart)
                }

            }

        }
    }
}