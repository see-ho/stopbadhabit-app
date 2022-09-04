package com.example.stopbadhabit.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stopbadhabit.ui.viewmodel.DiaryWriteViewModel
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.example.stopbadhabit.databinding.FragmentDiaryWriteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryWriteFragment : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentDiaryWriteBinding.inflate(layoutInflater) }
    companion object {
        fun newInstance() = DiaryWriteFragment()
    }

    private lateinit var viewModel: DiaryWriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}