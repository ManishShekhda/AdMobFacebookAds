package com.compose.admobads.Facebook.NativeAd

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import com.app.jetpackads.LibraryUtils.getActivity
import com.compose.admobads.Constant
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener

@Composable
fun rememberNativeAdCustom(
    adUInt: String,
    adListener: NativeAdListener
) = LocalContext.current.getActivity()?.let {
    remember(adUInt) {
        NativeAdState(
            activity = it,
            adUInt = adUInt,
            adListener = adListener
        )

    }
}

class NativeAdState(
    activity: Activity,
    adUInt: String,
    adListener: NativeAdListener?
) {
    val nativeAd = MutableLiveData<NativeAd?>()

    init {
       val  nativeAds = NativeAd(activity,Constant.FACEBOOK_NATIVE_AD_ID)
        val nativeAdListener = object : NativeAdListener{
            override fun onError(p0: Ad?, p1: AdError?) {
                if (p1 != null) {
                    Log.d("TAG", "onError: "+p1.errorMessage)
                }
            }

            override fun onAdLoaded(p0: Ad?) {
                if (nativeAds == null || nativeAds != p0) {
                    return;
                }
                Log.d("TAG", "onAdLoaded: ")
                nativeAd.postValue(nativeAds)
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }

            override fun onMediaDownloaded(p0: Ad?) {

            }

        }

        nativeAds.loadAd(nativeAds.buildLoadAdConfig().withAdListener(nativeAdListener).build())

    }
}