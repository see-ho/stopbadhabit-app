package com.seeho.stopbadhabit.util.navi

import com.seeho.stopbadhabit.util.fragment.MainFragmentType

interface FragmentNavigator {
    fun navigateTo(screen: MainFragmentType)
    fun init()
}