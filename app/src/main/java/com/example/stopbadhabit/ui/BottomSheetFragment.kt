package com.example.stopbadhabit.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val items = requireActivity().resources.getStringArray(R.array.goaldate_array)
        //val items = arrayOf("15일","30일","50일","100일")

        val mAdapter = ArrayAdapter(binding.root.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)

        binding.spinner.adapter = mAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        Log.e("item","${items[position]}")
                    }
                    1 -> {
                        Log.e("item","${items[position]}")
                    }
                    2 ->{
                        Log.e("item","${items[position]}")
                    }
                    3 -> {
                        Log.e("item","${items[position]}")
                    }
                    else -> {

                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btnAdd.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "HabitAddBottomSheet"
    }

    fun getInstance(): BottomSheetFragment {
        return BottomSheetFragment()
    }
}