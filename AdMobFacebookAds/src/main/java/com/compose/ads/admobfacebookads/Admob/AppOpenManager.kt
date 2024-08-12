package com.compose.ads.admobfacebookads.Admob

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.compose.ads.admobfacebookads.AdType
import com.compose.ads.admobfacebookads.Constant
import com.compose.ads.admobfacebookads.handlers.OnShowAdCompleteListener
import com.facebook.ads.Ad
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import java.util.Date

class AppOpenManager() : Application.ActivityLifecycleCallbacks, LifecycleObserver {

    var TAG = "AppOpenManager"
    var currentActivity: Activity? = null
    var appOpenAd: AppOpenAd? = null
    var isLoadingAd = false
    var isShowingAd = false
    var loadTime: Long = 0


    companion object {

        private var mInstance: AppOpenManager? = null

        fun getInstance(context: Context): AppOpenManager {

            return mInstance ?: synchronized(this) {
                mInstance ?: AppOpenManager().also { mInstance = it }
            }
        }
    }


    fun loadAd(context: Context) {
        if (isLoadingAd || isAdAvailable) {
            return
        }
        isLoadingAd = true
        val request = AdRequest.Builder().build()
        AppOpenAd.load(context, if (Constant.IS_TEST) {
            "ca-app-pub-3940256099942544/9257395921"
        } else {
            Constant.ADMOB_APP_OPEN_AD_ID
        },
            request,
            object : AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    Log.d(TAG, "onAdLoaded: ")
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.d(TAG, "onAdFailedToLoad: " + loadAdError.message)
                    isLoadingAd = false
                }
            })
    }

    fun showAppOpenAd(onShowAdCompleteListener: OnShowAdCompleteListener) {
        Log.d(TAG, "showAppOpenAd: " + currentActivity)

        if (Constant.AD_TYPE == AdType.ADMOB){

            if (!Constant.AD_SHOW) {
                onShowAdCompleteListener.onShowAdComplete()
                return
            }

            if (currentActivity != null) {
                if (isShowingAd) {
                    onShowAdCompleteListener.onShowAdComplete()
                    return
                }
                if (!isAdAvailable) {
                    if (isLoadingAd || isAdAvailable) {
                        onShowAdCompleteListener.onShowAdComplete()
                        return
                    }
                    isLoadingAd = true
                    val request = AdRequest.Builder().build()
                    AppOpenAd.load(currentActivity!!, if (Constant.IS_TEST) {
                        "ca-app-pub-3940256099942544/9257395921"
                    } else {
                        Constant.ADMOB_APP_OPEN_AD_ID
                    }, request,
                        object : AppOpenAdLoadCallback() {
                            override fun onAdLoaded(ad: AppOpenAd) {
                                Log.d(TAG, "onAdLoaded: ")
                                appOpenAd = ad
                                isLoadingAd = false
                                loadTime = Date().time
                                appOpenAd!!.fullScreenContentCallback =
                                    object : FullScreenContentCallback() {
                                        override fun onAdDismissedFullScreenContent() {
                                            appOpenAd = null
                                            isShowingAd = false
                                            onShowAdCompleteListener.onShowAdComplete()
                                            loadAd(currentActivity!!)
                                        }

                                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                            Log.d(
                                                TAG,
                                                "onAdFailedToShowFullScreenContent: " + adError.message
                                            )
                                            appOpenAd = null
                                            isShowingAd = false
                                            onShowAdCompleteListener.onShowAdComplete()
                                            loadAd(currentActivity!!)
                                        }

                                        override fun onAdShowedFullScreenContent() {
                                            loadAd(currentActivity!!)
                                        }
                                    }
                                isShowingAd = true
                                appOpenAd!!.show(currentActivity!!)
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                Log.d(TAG, "onAdFailedToLoad: " + loadAdError.message)
                                isLoadingAd = false
                                onShowAdCompleteListener.onShowAdComplete()
                            }
                        })
                    return
                }
                appOpenAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null
                        isShowingAd = false
                        onShowAdCompleteListener.onShowAdComplete()
                        loadAd(currentActivity!!)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.d(TAG, "onAdFailedToShowFullScreenContent: " + adError.message)
                        appOpenAd = null
                        isShowingAd = false
                        onShowAdCompleteListener.onShowAdComplete()
                        loadAd(currentActivity!!)
                    }

                    override fun onAdShowedFullScreenContent() {
                        loadAd(currentActivity!!)
                    }
                }
                isShowingAd = true
                appOpenAd!!.show(currentActivity!!)
            } else {
                onShowAdCompleteListener.onShowAdComplete()
            }
        }else if (Constant.AD_TYPE == AdType.FACEBOOK){
            if (!Constant.AD_SHOW) {
                onShowAdCompleteListener.onShowAdComplete()
                return
            }

            if (currentActivity != null) {
                var mInterstitialAd: InterstitialAd? = null

                mInterstitialAd = InterstitialAd(
                    currentActivity, if (Constant.IS_TEST) {
                        "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
                    } else {
                        Constant.FACEBOOK_INTERSTITIAL_AD_ID
                    }
                )
                val interstitialAdListener = object : InterstitialAdListener {
                    override fun onError(p0: Ad?, p1: com.facebook.ads.AdError?) {
                        Log.d(TAG, p1.toString())
                        mInterstitialAd = null
                        onShowAdCompleteListener.onShowAdComplete()
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
                        onShowAdCompleteListener.onShowAdComplete()
                    }
                }
                mInterstitialAd!!.loadAd(
                    mInterstitialAd!!.buildLoadAdConfig().withAdListener(interstitialAdListener).build()
                );

            } else {
                onShowAdCompleteListener.onShowAdComplete()
            }
        }

    }


    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private val isAdAvailable: Boolean
        private get() = appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.d(TAG, "onMoveToForeground: ")
        showAppOpenAd(object : OnShowAdCompleteListener {
            override fun onShowAdComplete() {

            }
        })
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (!isShowingAd) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}