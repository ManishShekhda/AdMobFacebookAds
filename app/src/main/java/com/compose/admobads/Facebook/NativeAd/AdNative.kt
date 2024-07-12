package com.compose.admobads.Facebook.NativeAd

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.compose.admobads.Constant
import com.compose.admobads.handlers.OnShowBanner
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAdListener

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
    val rememberCustomNativeAd = rememberNativeAdCustom(
        adUInt = Constant.FACEBOOK_NATIVE_AD_ID,
        adListener = object : NativeAdListener {

            override fun onError(p0: Ad?, p1: AdError?) {
                if (p1 != null) {
                    Log.d("TAG", "AdError: "+p1.errorMessage)
                    nativeAD.updateNativeAdState(
                        AdState(
                            isError = true,
                            errorMessage = p1.errorMessage
                        )
                    )
                }
            }

            override fun onAdLoaded(p0: Ad?) {
                Log.d("TAG", "onAdLoaded: ")
                nativeAD.updateNativeAdState(AdState(isSuccess = true))
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }

            override fun onMediaDownloaded(p0: Ad?) {

            }
        }
    )

    //Load Native Ad
    NativeAdViewSection(
        adType,
        nativeAdState = nativeAdState,
        rememberNativeAdState = rememberCustomNativeAd
    )


}


