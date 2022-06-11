package com.development.nest.studyshechdular.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.development.nest.studyshechdular.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

class NativeAdsHelper(private val activity: Context) {
    private var nativeAd: NativeAd? = null
    fun setNativeAd(
        shimmerFrameLayout: ShimmerFrameLayout,
        frameLayout: FrameLayout, layoutId: Int,
        addUnitId: String,
        onFail: ((String?) -> Unit)? = null,
        onLoad: ((NativeAd?) -> Unit)? = null,
    ) {
        if (!activity.isAlreadyPurchased()) {
            frameLayout.removeAllViews()
            val builder = AdLoader.Builder(
                activity,
                addUnitId
            )
            // OnUnifiedNativeAdLoadedListener implementation.
            builder.forNativeAd { unifiedNativeAd: NativeAd ->
                if (nativeAd != null) {
                    nativeAd!!.destroy()
                }
                nativeAd = unifiedNativeAd
                val adView =
                    (activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                        layoutId,
                        null
                    ) as NativeAdView
                populateUnifiedNativeAdView(unifiedNativeAd, adView)
                frameLayout.removeAllViews()
                frameLayout.addView(adView)
            }
            val videoOptions = VideoOptions.Builder().setStartMuted(true).build()
            val adOptions = NativeAdOptions.Builder().setVideoOptions(videoOptions)
                .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT).build()
            builder.withNativeAdOptions(adOptions)
            val adLoader = builder
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.e("Add Failed", loadAdError.message + "-ECode " + loadAdError.code)
                        //imageView?.visibility = View.VISIBLE

                        shimmerFrameLayout.visibility = View.VISIBLE
                        shimmerFrameLayout.startShimmer()
                        super.onAdFailedToLoad(loadAdError)
                        nativeAd?.let {
                            Log.e("Native Error", "Error")
                            //imageView?.visibility = View.VISIBLE

                            shimmerFrameLayout.visibility = View.VISIBLE
                            shimmerFrameLayout.startShimmer()
                            onFail?.invoke(loadAdError.message)
                        }
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        shimmerFrameLayout.stopShimmer()
                        shimmerFrameLayout.visibility = View.GONE

                        //imageView?.visibility = View.GONE
                        onLoad?.invoke(nativeAd!!)
                    }
                })
                .withNativeAdOptions(adOptions)
                .build()
            adLoader.loadAd(AdRequest.Builder().build())
        } else {

            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            frameLayout.visibility = View.GONE

        }


    }

    fun loadNativeAds(
        addUnitId: String,
        onLoad: ((NativeAd?) -> Unit)? = null,
        onFail: ((String?) -> Unit)? = null,
    ) {
        if (!activity.isAlreadyPurchased()) {
            val builder =
                AdLoader.Builder(
                    activity,
                    addUnitId
                )
            builder.forNativeAd { unifiedNativeAd: NativeAd? ->
                if (nativeAd != null) {
                    nativeAd!!.destroy()
                }
                nativeAd = unifiedNativeAd
                nativeAd?.let { onLoad?.invoke(it) }
            }
            val videoOptions = VideoOptions.Builder().setStartMuted(true).build()
            val adOptions =
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .setVideoOptions(videoOptions).build()
            builder.withNativeAdOptions(adOptions)
            val adLoader = builder
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.e("Add Failed", loadAdError.message + "-ECode " + loadAdError.code)
                        super.onAdFailedToLoad(loadAdError)
                        onFail?.invoke(loadAdError.message)
                    }
                })
                .withNativeAdOptions(adOptions)
                .build()
            adLoader.loadAd(AdRequest.Builder().build())

        }
        else{

        }
    }

    fun setLoadedNativeAd(
        frameLayout: FrameLayout,
        layoutId: Int,
        nativeAd: NativeAd,
        shimmerFrameLayout: ShimmerFrameLayout? = null,
    ) {
        if (!activity.isAlreadyPurchased()) {
            val adView =
                (activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    layoutId,
                    null
                ) as NativeAdView
            populateUnifiedNativeAdView(nativeAd, adView)
            frameLayout.removeAllViews()
            frameLayout.addView(adView)
            shimmerFrameLayout?.stopShimmer()
            shimmerFrameLayout?.visibility = View.GONE
            //frameLayout.visibility = View.VISIBLE



        } else {
            shimmerFrameLayout?.stopShimmer()
            shimmerFrameLayout?.visibility = View.GONE
            frameLayout.visibility = View.GONE
        }
    }



    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        val mediaView: MediaView = adView.findViewById(R.id.ad_media)
        adView.mediaView = mediaView
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
       // adView.starRatingView = adView.findViewById(R.id.ad_stars)
        //adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        adView.bodyView = adView.findViewById(R.id.ad_advertiser)
        adView.mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        if (adView.mediaView != null) {
            //adView.getMediaView().setVisibility(View.VISIBLE);
        } else {
            adView.mediaView?.visibility = View.INVISIBLE
        }
        if (nativeAd.headline != null) (adView.headlineView as TextView).text = nativeAd.headline
        if (nativeAd.callToAction == null) {
            adView.callToActionView?.visibility = View.INVISIBLE
        } else {
            adView.callToActionView?.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }
        if (nativeAd.icon != null) {
            adView.iconView?.visibility = View.VISIBLE
            (adView.iconView as ImageView).setImageDrawable(nativeAd.icon?.drawable)
        } else adView.iconView?.visibility = View.INVISIBLE
        if (nativeAd.starRating == null) {
            adView.starRatingView?.visibility = View.INVISIBLE
        }
        if (nativeAd.body == null) {
            adView.bodyView?.visibility = View.INVISIBLE
        } else {
            (adView.bodyView as TextView).text = nativeAd.body
            adView.bodyView?.visibility = View.VISIBLE
        }
        adView.setNativeAd(nativeAd)
    }


}