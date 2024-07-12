package com.compose.admobads

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import com.compose.admobads.Facebook.NativeAd.MainViewModel
import com.compose.admobads.Admob.AdBanner
import com.compose.admobads.Admob.AdInterstitial
import com.compose.admobads.handlers.OnShow
import com.compose.admobads.handlers.OnShowBanner
import com.compose.admobads.handlers.OnShowInterstitial
import com.google.android.gms.ads.MobileAds

class AdsShow(context: Context) {

    private var onShow: OnShow? = null

    companion object {

        private var mInstance: AdsShow? = null

        fun getInstance(context: Context): AdsShow {
            MobileAds.initialize(context) {}
            return mInstance ?: synchronized(this) {
                mInstance ?: AdsShow(context.applicationContext).also { mInstance = it }
            }
        }
    }

    fun ShowInterstitial(onShow: OnShow,activity: Activity) {

        this.onShow = onShow
        AdInterstitial.getInstance(activity).ShowInterstitilAd(activity, object : OnShowInterstitial {
            override fun show(show: Boolean) {
                onShow(show)
            }
        })
    }

    @Composable
    fun ShowBanner(onShow: OnShow, activity: Activity) {

        this.onShow = onShow
        AdBanner.getInstance(activity).ShowBannerAd(activity, object : OnShowBanner {
            override fun show(show: Boolean) {

            }
        })
    }

    @Composable
    fun ShowNative(mainViewModel: MainViewModel, activity: Activity, adType: Int) {

//        AdNative.getInstance(activity).ShowNativeAd(activity, adType,mainViewModel,object : OnShowBanner {
//            override fun show(show: Boolean) {
//
//            }
//        })

        com.compose.admobads.Facebook.NativeAd.AdNative.getInstance(activity).ShowNativeAd(
            activity = activity,
            adType = adType,
            mainViewModel = mainViewModel,
            onShowBanner = object : OnShowBanner {
                override fun show(show: Boolean) {

                }
            }
        )
    }


    private fun onShow(boolean: Boolean){
        if (onShow != null){
            onShow?.show(boolean)
            onShow = null
        }
    }
}