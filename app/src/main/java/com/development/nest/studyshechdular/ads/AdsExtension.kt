package com.development.nest.studyshechdular.ads

import android.app.Activity
import android.content.Context
import android.util.Log

//Ad Counters
private var counter = 1
private var counterTranslation = 1
private var counterDictionary = 1
private var counterMain = 1
private var counterSetting = 0
private var counterNotes = 1
private var counterCamResult = 1
private var counterCam = 1
fun Context.isAlreadyPurchasedNew(): Boolean {
    //  return TinyDB(applicationContext).getBoolean("qdqd")
    return false
}

fun Activity.showCamOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterCam == 1) {
            counterCam = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterCam++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showCamResultOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterCamResult == 1) {
            counterCamResult = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterCamResult++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showNotesOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterNotes == 1) {
            counterNotes = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterNotes++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showEvenSetting(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterSetting == 1) {
            counterSetting = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterSetting++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showMianOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterMain == 1) {
            counterMain = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterMain++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showDictionaryOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterDictionary == 1) {
            counterDictionary = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterDictionary++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showTranslationOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counterTranslation == 1) {
            counterTranslation = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterTranslation++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

fun Activity.showAdOdd(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (counter == 1) {
            counter = 0
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counter++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}

var counterBottomNav = 1
fun Activity.showAdBottomNav(afterAdWork: () -> Unit) {

    Log.e("counterBottomNav", counterBottomNav.toString())
    if (!isAlreadyPurchasedNew()) {
        if (counterBottomNav == 3) {
            counterBottomNav = 1
            if (isInterstitialLoaded()) {
                InterListener(afterAdWork)
                showInterstitial()
            } else {
                afterAdWork()
            }
        } else {
            counterBottomNav++
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}


fun Activity.showAdAndGoSplash(afterAdWork: () -> Unit) {
    if (!isAlreadyPurchasedNew()) {
        if (isSplashInterstitialLoaded()) {
            InterSplashListener(afterAdWork)
            showInterstitialForSplash()
        } else {
            afterAdWork()
        }
    } else {
        afterAdWork()
    }
}


fun Activity.showInterstitial() {
    InterstitialAdUpdated.getInstance().showInterstitialAdNew(this)
}

fun isInterstitialLoaded(): Boolean {
    return InterstitialAdUpdated.getInstance().getInter() != null
}

fun Activity.InterListener(afterAdWork: () -> Unit) {
    InterstitialAdUpdated.getInstance().setListener(this, afterAdWork)
}


fun Activity.showInterstitialForSplash() {
    InterstitialAdUpdatedForSplash.getInstance().showInterstitialAdNew(this)
}

fun isSplashInterstitialLoaded(): Boolean {
    return InterstitialAdUpdatedForSplash.getInstance().getInter() != null
}

fun Activity.InterSplashListener(afterAdWork: () -> Unit) {
    InterstitialAdUpdatedForSplash.getInstance().setListener(this, afterAdWork)
}