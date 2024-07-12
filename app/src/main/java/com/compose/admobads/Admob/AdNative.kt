package com.compose.admobads.Admob

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.compose.admobads.Admob.NativeAd.AdState
import com.compose.admobads.Admob.NativeAd.MainViewModel
import com.compose.admobads.handlers.OnShowBanner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.compose.admobads.Constant
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.NativeAdOptions

class AdNative(context: Context) {

    var context: Context = context
    var activity: Activity? = null
    private var onShowBanner: OnShowBanner? = null
    private final val TAG = "AdBanner"


    companion object {

        private var mInstance: AdNative? = null

        fun getInstance(context: Activity): AdNative {
            return mInstance ?: synchronized(this) {
                mInstance ?: AdNative(context.applicationContext).also { mInstance = it }
            }
        }
    }


    @Composable
    fun ShowNativeAd(
        activity: Activity,
        adType: Int,
        mainViewModel: MainViewModel,
        onShowBanner: OnShowBanner
    ) {
        this.onShowBanner = onShowBanner
        this.activity = activity

        if (Constant.AD_SHOW) {
            NativeContainer( mainViewModel,adType)
        } else {
            onShowNative(onShowBanner, false)
        }
    }


}

fun onShowNative(onShowBanner: OnShowBanner, boolean: Boolean) {
    onShowBanner.show(boolean)
}

@Composable
fun NativeContainer(nativeAD: MainViewModel, adType: Int) {
    val nativeAdState by nativeAD.nativeAdState.collectAsState()
    val rememberCustomNativeAd = com.compose.admobads.Admob.NativeAd.rememberNativeAdCustom(
        adUInt = Constant.ADMOB_NATIVE_AD_ID,
        adOptions = NativeAdOptions.Builder()
            .setVideoOptions(
                VideoOptions.Builder().setStartMuted(true)
                    .setClickToExpandRequested(true).build()
            ).setRequestMultipleImages(true).build(),
        adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                nativeAD.updateNativeAdState(AdState(isSuccess = true))
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                nativeAD.updateNativeAdState(
                    AdState(
                        isError = true,
                        errorMessage = p0.message
                    )
                )
            }
        }
    )

    //Load Native Ad
    com.compose.admobads.Admob.NativeAd.NativeAdViewSection(
        adType,
        nativeAdState = nativeAdState,
        rememberNativeAdState = rememberCustomNativeAd
    )


}


