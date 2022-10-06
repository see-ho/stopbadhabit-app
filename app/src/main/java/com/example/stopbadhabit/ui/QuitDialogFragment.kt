package com.example.stopbadhabit.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentHomeBinding
import com.example.stopbadhabit.databinding.FragmentQuitDialogBinding
import com.example.stopbadhabit.util.Utils

class QuitDialogFragment : DialogFragment() {
    private val binding by lazy { FragmentQuitDialogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        if (dialog != null && activity != null && isAdded) {
            val fullWidth = Utils.getScreenWidth(requireActivity()) * .7
            dialog?.window?.setLayout(fullWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
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
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvQuitNo.setOnClickListener {
            dismiss()
        }
        binding.tvQuitYes.setOnClickListener {
            val pid = android.os.Process.myPid()
            android.os.Process.killProcess(pid)
        }
    }

}