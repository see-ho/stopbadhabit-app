package com.example.stopbadhabit.ui

import android.animation.Animator
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
import kotlinx.coroutines.delay

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onCreate: testet", )

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.isLoading.value
            }
        }
        setContentView(binding.root)


        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHomeFragment.navController

        mainViewModel.heartlottie.observe(this){
            if (it == true){
                binding.lottieHeart.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        binding.lottieHeart.setOnClickListener {

                        }
                    }
                    override fun onAnimationEnd(animation: Animator) {
                        binding.lottieHeart.visibility = View.GONE
                        mainViewModel.doneHeartLottie()
                    }
                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }
                })
                binding.lottieHeart.visibility = View.VISIBLE
                binding.lottieHeart.playAnimation()
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