package com.example.stopbadhabit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.ivHistory.setOnClickListener {
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.fragmentContainer, HistoryFragment())
                addToBackStack(null)
                commit()
            }
        }



        val listener = OnLongClickListener { v ->
            Log.e("hihi","hihi")
            true
        }

        binding.tvTitle.setOnLongClickListener { it->
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.fragmentContainer, OpenSourceFragment())
                addToBackStack(null)
                commit()
            }
            true
        }

        binding.ivHabitAdd.setOnClickListener {
            val bundle = Bundle()
            val dialog: BottomSheetFragment = BottomSheetFragment().getInstance()
            dialog.arguments = bundle
            activity?.supportFragmentManager?.let {
                    fragmentManager -> dialog.show(
                fragmentManager,dialog.tag
            )
            }
        }
        return binding.root
    }
}