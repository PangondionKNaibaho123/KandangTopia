package com.example.kandangtopia.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val TAG = SplashScreenActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSplashProcess()
    }

    private fun setUpSplashProcess(){
        Handler().postDelayed(
            {
                startActivity(HomeActivity.newIntent(this))
                finish()
            },4000
        )
    }
}