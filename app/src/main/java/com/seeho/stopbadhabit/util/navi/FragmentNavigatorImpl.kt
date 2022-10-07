/*
package com.example.stopbadhabit.util.navi

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.stopbadhabit.R
import com.example.stopbadhabit.ui.HabitDetailFragment
import com.example.stopbadhabit.ui.HistoryFragment
import com.example.stopbadhabit.ui.HomeFragment
import com.example.stopbadhabit.ui.OpenSourceFragment
import com.example.stopbadhabit.util.fragment.MainFragmentType

class FragmentNavigatorImpl(private val activity:FragmentActivity) : FragmentNavigator {
    private val homeFragment = HomeFragment.newInstance(null)
    private val historyFragment = HistoryFragment.newInstance(null)
    private val habitDetailFragment = HabitDetailFragment.newInstance(null)
    private val openSourceFragment = OpenSourceFragment.newInstance(null)

    override fun navigateTo(screen: MainFragmentType) {
        replaceFragment(screen)
    }

    override fun init() {
        activity.supportFragmentManager.commit {
            replace(R.id.fragmentContainer,homeFragment)
        }
    }

    private fun replaceFragment(screen: MainFragmentType){
        activity.supportFragmentManager.commit {
            when(screen) {
                MainFragmentType.Home -> {
                    replace(R.id.fragmentContainer,homeFragment)
                }

                MainFragmentType.History -> {
                    replace(R.id.fragmentContainer,historyFragment)
                }

                MainFragmentType.HabitDetail -> {
                    replace(R.id.fragmentContainer,habitDetailFragment)
                }

                MainFragmentType.OpenSource -> {
                    replace(R.id.fragmentContainer,openSourceFragment)
                }
            }
        }
    }
}*/
