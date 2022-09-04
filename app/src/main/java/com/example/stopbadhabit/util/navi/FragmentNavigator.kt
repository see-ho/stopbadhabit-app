package com.example.stopbadhabit.util.navi

import com.example.stopbadhabit.util.fragment.MainFragmentType

interface FragmentNavigator {
    fun navigateTo(screen: MainFragmentType)
    fun init()
}