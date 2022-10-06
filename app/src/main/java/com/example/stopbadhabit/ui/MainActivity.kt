package com.example.stopbadhabit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModel

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.stopbadhabit.R
import com.example.stopbadhabit.databinding.ActivityMainBinding
import com.example.stopbadhabit.databinding.FragmentHomeBinding
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.isLoading.value
            }
        }

        setContentView(R.layout.activity_main)
        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHomeFragment.navController

        mainViewModel.heartlottie.observe(this){
            if (it == true){
                binding.lottieHeart.visibility = View.VISIBLE
                binding.testText.visibility = View.VISIBLE
                //binding.lottieHeart.playAnimation()
                //mainViewModel.doneHeartLottie()
            }
            else if (it == false){
             //   binding.lottieHeart.visibility = View.INVISIBLE
            }
        }

    }

    override fun onBackPressed() {
        if(navController.currentDestination?.id == R.id.homeFragment){
            QuitDialogFragment().show(
                supportFragmentManager,"quit"
            )
        }else
            super.onBackPressed()
    }

}