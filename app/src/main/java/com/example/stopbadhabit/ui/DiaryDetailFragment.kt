package com.example.stopbadhabit.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.example.stopbadhabit.databinding.FragmentDiaryDetailBinding
import com.example.stopbadhabit.ui.viewmodel.DiaryDetailViewModel
import com.example.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryDetailFragment : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentDiaryDetailBinding.inflate(layoutInflater) }

    private val habitDetailViewModel : HabitDetailViewModel by viewModels()
    private val diaryDetailViewModel : DiaryDetailViewModel by viewModels()

    private val args: DiaryDetailFragmentArgs by navArgs()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = (super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        })as BottomSheetDialog
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        diaryDetailViewModel.diary.observe(viewLifecycleOwner){
            with(binding){
                tvDdEmotion.text = it.emotion
                tvDdSituation.text = it.situation
                tvDdReason.text = it.reason
                tvDdPromise.text =it.promise
                tvDdDate.text = it.diary_date
            }
        }
    }

    private fun setObserver(){
        diaryDetailViewModel.getDiaryDetail(args.diaryId)
    }

}