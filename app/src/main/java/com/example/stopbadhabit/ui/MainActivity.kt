package com.example.stopbadhabit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.ActivityMainBinding
import com.example.stopbadhabit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHomeFragment.navController
    }

    /*override fun onStart() {
        super.onStart()
        viewModel.ui.observe(this,EventObserver{
            when(it) {
                MainState
            }
        })

    }

    init {
        navi.init()
    }*/
}