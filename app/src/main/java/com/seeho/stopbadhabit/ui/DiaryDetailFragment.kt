package com.seeho.stopbadhabit.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.seeho.stopbadhabit.databinding.FragmentDiaryDetailBinding
import com.seeho.stopbadhabit.ui.viewmodel.DiaryDetailViewModel
import com.seeho.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryDetailFragment : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentDiaryDetailBinding.inflate(layoutInflater) }
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