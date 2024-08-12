package com.compose.ads.admobfacebookads.Admob.NativeAd

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.compose.ads.admobfacebookads.Constant
import com.compose.ads.admobfacebookads.handlers.OnShow
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MediaAspectRatio
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class AdMobNativeAd() {

    companion object {

        private var mInstance: AdMobNativeAd? = null

        fun getInstance(context: Context): AdMobNativeAd {
            MobileAds.initialize(context) {}
            return mInstance ?: synchronized(this) {
                mInstance ?: AdMobNativeAd().also { mInstance = it }
            }
        }
    }

    @Composable
    fun ShowAdMobNativeBig(activity: Activity, onShow: OnShow) {
        var isNativeLoaded by remember {
            mutableStateOf(false)
        }

        var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

        if (isNativeLoaded) {
            ShowAdmobNativeAd(nativeAd = nativeAd)
        }

        val adLoader = AdLoader.Builder(
            activity, if (Constant.IS_TEST) {
                "ca-app-pub-3940256099942544/2247696110"
            } else {
                Constant.ADMOB_NATIVE_AD_ID
            }
        ).forNativeAd {
            nativeAd = it
            isNativeLoaded = true
            onShow.show(true)
            Log.d("TAG", "adLoader: admob")

        }.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                onShow.show(false)
                Log.d("TAG", "LoadAdError: admob")
            }
        }).withNativeAdOptions(
            NativeAdOptions.Builder().setRequestMultipleImages(true).setAdChoicesPlacement(1)
                .setMediaAspectRatio(MediaAspectRatio.ANY).build()
        ).build()

        LaunchedEffect(adLoader) {
            if (!isNativeLoaded) {
                adLoader.loadAd(AdRequest.Builder().build())
            }
        }
    }

    @Composable
    fun ShowAdMobNativeSmall(activity: Activity, onShow: OnShow) {
        var isNativeLoaded by remember {
            mutableStateOf(false)
        }

        var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

        if (isNativeLoaded) {
            ShowSmallNativeAdView(nativeAd = nativeAd)
        }

        val adLoader = AdLoader.Builder(
            activity, if (Constant.IS_TEST) {
                "ca-app-pub-3940256099942544/2247696110"
            } else {
                Constant.ADMOB_NATIVE_AD_ID
            }
        ).forNativeAd {
            nativeAd = it
            isNativeLoaded = true
            onShow.show(true)
            Log.d("TAG", "adLoader: admob")

        }.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                onShow.show(false)
                Log.d("TAG", "LoadAdError: admob")
            }
        }).withNativeAdOptions(
            NativeAdOptions.Builder().setRequestMultipleImages(true).setAdChoicesPlacement(1)
                .setMediaAspectRatio(MediaAspectRatio.ANY).build()
        ).build()

        LaunchedEffect(adLoader) {
            if (!isNativeLoaded) {
                adLoader.loadAd(AdRequest.Builder().build())
            }
        }
    }

}