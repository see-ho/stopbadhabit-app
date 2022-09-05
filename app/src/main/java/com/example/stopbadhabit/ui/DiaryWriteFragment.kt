package com.example.stopbadhabit.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.stopbadhabit.ui.viewmodel.DiaryWriteViewModel
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.example.stopbadhabit.databinding.FragmentDiaryWriteBinding
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryWriteFragment : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentDiaryWriteBinding.inflate(layoutInflater) }
    private val mainViewModel : MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = DiaryWriteFragment()
    }

    private lateinit var viewModel: DiaryWriteViewModel

    private var diary_date : String =""
    private var situation : String=""
    private var reason : String=""
    private var emotion : String=""
    private var promise : String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnDwfAdd.setOnClickListener {
            if(setDiary() == 1) {
                Log.e(javaClass.simpleName, "onCreateView: 성공했단다", )
                mainViewModel.insertDiary(Diary(
                    situation=situation,
                    reason = reason,
                    emotion = emotion,
                    promise = promise,
                    habit_id = 1,
                    diary_date = "2022-09-05"
                ))
                dismiss()
            }
            else
                Snackbar.make(binding.root,String.format(requireActivity().resources.getString(R.string.dw_setPromise)), Snackbar.LENGTH_SHORT).show()

    }

        return binding.root
    }

    private fun setDiary() : Int{
        situation= binding.etSituation.text.toString()
        reason= binding.etReason.text.toString()
        emotion= binding.etEmotion.text.toString()
        if(binding.etPromise.text.toString() != "")
            promise=binding.etPromise.text.toString()
        else
            return -1

        return 1
    }

}