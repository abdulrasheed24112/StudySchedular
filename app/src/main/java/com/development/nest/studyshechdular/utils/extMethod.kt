package com.development.nest.studyshechdular.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.development.nest.studyshechdular.ads.InterstitialAdUpdated

private var counter: Int = 1

var COUNTER = 1
var isInterstitialShown = false

fun Activity.sendEmail() {
    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data = Uri.parse("mailto:")
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("developmentnest85@gmail.com"))
    mIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Student Scheduler")
    mIntent.putExtra(
        Intent.EXTRA_TEXT,
        "Tell us which issues you are facing using Student Scheduler App?"
    )
    startActivity(Intent.createChooser(mIntent, "Send Email"))
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.showInterstitialAd() {
    InterstitialAdUpdated.getInstance().showInterstitialAdNew(this)
}

fun Activity.counterInterstitial() {
    counter++
    if (counter == 2) {
        showInterstitialAd()
        counter = 0
    }

}


fun Context.isInternetAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo: NetworkInfo? = null
    activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}

fun Context.isAlreadyPurchased(): Boolean {
    return false

}