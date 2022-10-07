package com.seeho.stopbadhabit.ui

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.databinding.ActivityMainBinding
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel
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