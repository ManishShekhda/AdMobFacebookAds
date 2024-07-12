package com.compose.admobads.Facebook

import android.app.Activity
import android.content.Context
import android.util.Log
import com.compose.admobads.Constant
import com.compose.admobads.handlers.OnShowInterstitial
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdInterstitial(context: Context) {

    var context: Context = context
    private var mInterstitialAd: InterstitialAd? = null
    private var onShowInterstitial: OnShowInterstitial? = null
    private final val TAG = "AdInterstitial"

    companion object {

        private var mInstance: AdInterstitial? = null

        fun getInstance(context: Activity): AdInterstitial {
            return mInstance ?: synchronized(this) {
                mInstance ?: AdInterstitial(context.applicationContext).also { mInstance = it }
            }
        }
    }


    fun ShowInterstitilAd(activity: Activity, onShowInterstitial: OnShowInterstitial) {
        this.onShowInterstitial = onShowInterstitial

        if (Constant.AD_SHOW) {

            mInterstitialAd = InterstitialAd(context,Constant.FACEBOOK_INTERSTITIAL_AD_ID)
            val interstitialAdListener = object : InterstitialAdListener {
                override fun onError(p0: Ad?, p1: AdError?) {
                    Log.d(TAG, p1.toString())
                    mInterstitialAd = null
                    onShowInterstitial(false)
                }

                override fun onAdLoaded(p0: Ad?) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd!!.show()
                }

                override fun onAdClicked(p0: Ad?) {

                }

                override fun onLoggingImpression(p0: Ad?) {

                }

                override fun onInterstitialDisplayed(p0: Ad?) {

                }

                override fun onInterstitialDismissed(p0: Ad?) {
                    mInterstitialAd = null
                    onShowInterstitial(true)
                }
            }
            mInterstitialAd!!.loadAd(mInterstitialAd!!.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

        } else {
            onShowInterstitial(false)
        }
    }

    private fun onShowInterstitial(boolean: Boolean) {
        if (onShowInterstitial != null) {
            onShowInterstitial?.show(boolean)
            onShowInterstitial = null
        }
    }
}