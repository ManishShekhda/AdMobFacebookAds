package com.compose.admobads.Admob

import android.app.Activity
import android.content.Context
import android.util.Log
import com.compose.admobads.Constant
import com.compose.admobads.handlers.OnShowInterstitial
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
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

    fun LoadAds() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            if (Constant.IS_TEST) {
                "ca-app-pub-3940256099942544/8691691433"
            } else {
                Constant.ADMOB_INTERSTITIAL_AD_ID
            },
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }

    fun ShowInterstitilAd(activity: Activity, onShowInterstitial: OnShowInterstitial) {
        this.onShowInterstitial = onShowInterstitial

        if (Constant.AD_SHOW) {
            if (mInterstitialAd != null) {
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d(TAG, "Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                        LoadAds()
                        onShowInterstitial(true)
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.")
                        mInterstitialAd = null
                        LoadAds()
                        onShowInterstitial(false)
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.")
                    }
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd!!.show(activity)
                } else {
                    LoadAds()
                    onShowInterstitial(false)
                }
            } else {
                val adRequest = AdRequest.Builder().build()
                InterstitialAd.load(
                    context,
                    if (Constant.IS_TEST) {
                        "ca-app-pub-3940256099942544/8691691433"
                    } else {
                        Constant.ADMOB_INTERSTITIAL_AD_ID
                    },
                    adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            Log.d(TAG, adError.toString())
                            mInterstitialAd = null
                            LoadAds()
                            onShowInterstitial(false)
                        }

                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            Log.d(TAG, "Ad was loaded.")
                            mInterstitialAd = interstitialAd
                            mInterstitialAd?.fullScreenContentCallback =
                                object : FullScreenContentCallback() {
                                    override fun onAdClicked() {
                                        // Called when a click is recorded for an ad.
                                        Log.d(TAG, "Ad was clicked.")
                                    }

                                    override fun onAdDismissedFullScreenContent() {
                                        // Called when ad is dismissed.
                                        Log.d(TAG, "Ad dismissed fullscreen content.")
                                        mInterstitialAd = null
                                        LoadAds()
                                        onShowInterstitial(true)
                                    }

                                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                                        // Called when ad fails to show.
                                        Log.e(TAG, "Ad failed to show fullscreen content.")
                                        mInterstitialAd = null
                                        LoadAds()
                                        onShowInterstitial(false)
                                    }

                                    override fun onAdImpression() {
                                        // Called when an impression is recorded for an ad.
                                        Log.d(TAG, "Ad recorded an impression.")
                                    }

                                    override fun onAdShowedFullScreenContent() {
                                        // Called when ad is shown.
                                        Log.d(TAG, "Ad showed fullscreen content.")
                                    }
                                }
                            if (mInterstitialAd != null) {
                                mInterstitialAd!!.show(activity)
                            } else {
                                LoadAds()
                                onShowInterstitial(false)
                            }
                        }
                    })
            }

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