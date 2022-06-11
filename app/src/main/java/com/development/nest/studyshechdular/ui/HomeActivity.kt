package com.development.nest.studyshechdular.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.development.nest.studyshechdular.BuildConfig
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.databinding.ActivityHomeBinding
import com.development.nest.studyshechdular.utils.BottomRating
import com.development.nest.studyshechdular.utils.NativeAdsHelper

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    lateinit var appContext: Context
    var cvNotes: CardView? = null
    var cvSchedule: CardView? = null
    var cvGPA: CardView? = null
    var cvCalculator: CardView? = null
    var share: ImageView? = null
    var rate: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        appContext = applicationContext
        displayNative()
        cvNotes = findViewById(R.id.cvNotes)
        share = findViewById(R.id.share_app)
        rate = findViewById(R.id.rate_us)
        cvSchedule = findViewById(R.id.cvSchedule)
        cvGPA = findViewById(R.id.cvGPA)
        cvCalculator = findViewById(R.id.cvCalculator)
        cvSchedule?.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SchedulerActivity::class.java
                )
            )
        }
        cvNotes?.setOnClickListener(View.OnClickListener { view: View? ->
            startActivity(
                Intent(
                    this,
                    NotesActivity::class.java
                )
            )
        })
        cvGPA?.setOnClickListener(View.OnClickListener { view: View? ->
            startActivity(
                Intent(
                    this,
                    CgpaActivity::class.java
                )
            )
        })
        cvCalculator?.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    CalculatorActivity::class.java
                )
            )
        }
        share?.setOnClickListener {
            shareApp()
        }
        rate?.setOnClickListener {
            val materialRatingApp = BottomRating(this)
            materialRatingApp.showNow(supportFragmentManager, "")
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cvNotes -> startActivity(Intent(this, SchedulerActivity::class.java))
            R.id.cvCalculator -> startActivity(Intent(this, NotesActivity::class.java))
            R.id.cvSchedule -> {
            }
            R.id.cvGPA -> {
            }
        }
    }

    companion object {
        var appContext: Context? = null
            private set

        @JvmStatic
        fun getPx(context: Context, dimensionDp: Int): Int {
            val density = context.resources.displayMetrics.density
            return (dimensionDp * density + 0.5f).toInt()
        }
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, " Student Scheduler App")
            var shareMessage = "\nLet me recommend you this application\n\n"
            shareMessage =
                """
        ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
        """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
        }
    }

    private fun displayNative() {
        with(binding) {
            NativeAdsHelper(this@HomeActivity).setNativeAd(
                smallAdLayout.splashShimmer,
                smallAdLayout.nativeAdContainerView,
                R.layout.small_native_ad,
                getString(R.string.native_id)
            )
        }
    }


}


