package com.example.stopbadhabit.ui

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.stopbadhabit.ui.viewmodel.DiaryWriteViewModel
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.example.stopbadhabit.databinding.FragmentDiaryWriteBinding
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DiaryWriteFragment : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentDiaryWriteBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by activityViewModels()
    private val diaryWriteViewModel: DiaryWriteViewModel by viewModels()
    private var detailId = -1

    companion object {
        fun newInstance() = DiaryWriteFragment()
    }

    private lateinit var viewModel: DiaryWriteViewModel

    private var diary_date: String = ""
    private var situation: String = ""
    private var reason: String = ""
    private var emotion: String = ""
    private var promise: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.detailHabitId.observe(viewLifecycleOwner){
            if(it != -1)
                diaryWriteViewModel.getHabitDetail(it)
            else
                Log.e("fsda", "onCreateView: sdfsdf", )
        }

        diaryWriteViewModel.habit.observe(viewLifecycleOwner){
            binding.tvHwName.text = it.name
        }

        binding.btnDwfAdd.setOnClickListener {
            setDiary()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.detailHabitId.observe(viewLifecycleOwner) {
            detailId = it
            diaryWriteViewModel.getHabitDetail(it)
        }
        binding.tvHwName.text = diaryWriteViewModel.habit.value?.name
        binding.tvTodayDate.text=LocalDate.now().toString()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = (super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        })as BottomSheetDialog
        //dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) as BottomSheetDialog
        return dialog
    }


    private fun setDiary() {
        if (binding.etPromise.text.toString() == "") {
            Snackbar.make(
                binding.root,
                String.format(requireActivity().resources.getString(R.string.dw_setPromise)),
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        else {
            promise = binding.etPromise.text.toString()
            situation = binding.etSituation.text.toString()
            reason = binding.etReason.text.toString()
            emotion = binding.etEmotion.text.toString()
            diaryWriteViewModel.updateLife()
            mainViewModel.insertDiary(
                Diary(
                    situation = situation,
                    reason = reason,
                    emotion = emotion,
                    promise = promise,
                    habit_id = detailId,
                    diary_date = LocalDate.now().toString()
                ), detailId, diaryWriteViewModel.habit.value
            )
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        mainViewModel.setDetailId(detailId)
    }

}