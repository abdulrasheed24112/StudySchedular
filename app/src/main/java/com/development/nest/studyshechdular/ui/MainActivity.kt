package com.development.nest.studyshechdular.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.ads.InterstitialAdUpdatedForSplash
import com.development.nest.studyshechdular.ads.showAdAndGoSplash
import com.development.nest.studyshechdular.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val SPLASH_TIME = 7000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        InterstitialAdUpdatedForSplash.getInstance().loadInterstitialAd(this)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressLoading.visibility = View.GONE
            binding.getStarted.visibility = View.VISIBLE
        }, SPLASH_TIME.toLong())
        binding.getStarted.setOnClickListener {
            showAdAndGoSplash {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}