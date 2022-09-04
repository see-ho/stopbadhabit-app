package com.example.stopbadhabit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stopbadhabit.R


class OpenSourceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_source, container, false)
    }

    companion object {
        fun newInstance(bundle: Bundle?): OpenSourceFragment {
            val fragment = OpenSourceFragment()
            bundle?.let {
                fragment.arguments = it
            }
            return fragment
        }
    }

}