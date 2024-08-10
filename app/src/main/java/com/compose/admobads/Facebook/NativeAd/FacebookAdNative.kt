package com.compose.admobads.Facebook.NativeAd

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.compose.admobads.Constant
import com.compose.admobads.handlers.OnShow
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdBase
import com.facebook.ads.NativeAdListener
import com.facebook.ads.NativeBannerAd
import com.google.android.gms.ads.MobileAds

class FacebookAdNative(context: Context) {

    companion object {

        private var mInstance: FacebookAdNative? = null

        fun getInstance(context: Context): FacebookAdNative {
            MobileAds.initialize(context) {}
            return mInstance ?: synchronized(this) {
                mInstance ?: FacebookAdNative(context.applicationContext).also { mInstance = it }
            }
        }
    }

    @Composable
    fun ShowFacebookNativeBig(activity: Activity, onShow: OnShow) {
        var isNativeLoaded by remember {
            mutableStateOf(false)
        }
        var nativeAd by remember {
            mutableStateOf<NativeAd?>(null)
        }

        if (isNativeLoaded) {
            ShowNativeAdView(nativeAd = nativeAd, activity = activity)
        }

        LaunchedEffect(Unit) {
            val nativeAds = NativeAd(
                activity, if (Constant.IS_TEST) {
                    "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
                } else {
                    Constant.FACEBOOK_NATIVE_AD_ID
                }
            )
            val nativeAdListener = object : NativeAdListener {

                override fun onError(p0: Ad?, p1: AdError?) {
                    if (p1 != null) {
                        Log.d("TAG", "onError: facebook" + p1.errorMessage)
                    }
                    onShow.show(false)
                }

                override fun onAdLoaded(p0: Ad?) {
                    if (nativeAds == null || nativeAds != p0) {
                        return;
                    }
                    Log.d("TAG", "onAdLoaded: facebook")
                    nativeAd = nativeAds
                    isNativeLoaded = true
                    onShow.show(true)
                }

                override fun onAdClicked(p0: Ad?) {

                }

                override fun onLoggingImpression(p0: Ad?) {

                }

                override fun onMediaDownloaded(p0: Ad?) {

                }

            }

            nativeAds.loadAd(
                nativeAds.buildLoadAdConfig().withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                    .withAdListener(nativeAdListener).build()
            )
        }

    }


    @Composable
    fun ShowFacebookNativeSmall(activity: Activity, onShow: OnShow) {
        var isNativeLoaded by remember {
            mutableStateOf(false)
        }
        var nativeAd by remember {
            mutableStateOf<NativeBannerAd?>(null)
        }

        if (isNativeLoaded) {
            ShowNativeAdViewSmall(nativeAd = nativeAd, activity = activity)
        }

        LaunchedEffect(Unit) {
            val nativeAds = NativeBannerAd(
                activity, if (Constant.IS_TEST) {
                    "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
                } else {
                    Constant.FACEBOOK_NATIVE_BANNER_AD_ID
                }
            )
            val nativeAdListener = object : NativeAdListener {

                override fun onError(p0: Ad?, p1: AdError?) {
                    if (p1 != null) {
                        Log.d("TAG", "onError: facebook" + p1.errorMessage)
                    }
                    onShow.show(false)
                }

                override fun onAdLoaded(p0: Ad?) {
                    if (nativeAds == null || nativeAds != p0) {
                        return;
                    }
                    Log.d("TAG", "onAdLoaded: facebook")
                    nativeAd = nativeAds
                    isNativeLoaded = true
                    onShow.show(true)
                }

                override fun onAdClicked(p0: Ad?) {

                }

                override fun onLoggingImpression(p0: Ad?) {

                }

                override fun onMediaDownloaded(p0: Ad?) {

                }

            }

            nativeAds.loadAd(
                nativeAds.buildLoadAdConfig().withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                    .withAdListener(nativeAdListener).build()
            )
        }

    }

}