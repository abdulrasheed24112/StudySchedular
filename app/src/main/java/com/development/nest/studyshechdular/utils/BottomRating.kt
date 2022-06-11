package com.development.nest.studyshechdular.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.development.nest.studyshechdular.BuildConfig
import com.development.nest.studyshechdular.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomRating(activity: Activity) : BottomSheetDialogFragment() {
    private var ratingBar: RatingBar? = null
    private var rate_emoji: View? = null
    private var rate_result_tip: TextView? = null
    private var rate_result_title: TextView? = null
    private var resultText: TextView? = null
    private var layout_share: ConstraintLayout? = null
    private var layout_cancel: ConstraintLayout? = null
    var rateLike = 0f


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.lib_rate_round_corner)
        return super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.rating_bottom_sheet, container, false)
        rate_result_title = v.findViewById(R.id.rate_result_title)
        rate_result_tip = v.findViewById(R.id.rate_result_tip)
        layout_share = v.findViewById(R.id.lib_rate_button)
        layout_cancel = v.findViewById(R.id.layout_cancel)
        ratingBar = v.findViewById(R.id.pdfRatingBar)
        rate_emoji = v.findViewById(R.id.rate_emoji)
        resultText = v.findViewById(R.id.btn_txt)
        clickMethods()
        return v
    }

    private fun clickMethods() {
        ratingBar?.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                rateLike = rating
                if (rateLike > 4) {
                    resultText?.text = "Continue"
                } else {
                    resultText?.text = "Feedback"
                }
            }
        layout_share!!.setOnClickListener { view: View? ->
            if (rateLike > 4) {
                rateUs()
                dialog?.dismiss()
            } else {
                dialog?.dismiss()
                requireActivity().sendEmail()
            }
        }
        layout_cancel?.setOnClickListener {
            dismiss()

        }
    }

    fun rateUs() {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
        )
        startActivity(browserIntent)
    }
}