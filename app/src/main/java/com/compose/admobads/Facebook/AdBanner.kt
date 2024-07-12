package com.compose.admobads.Facebook

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.compose.admobads.Constant
import com.compose.admobads.handlers.OnShowBanner
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AdListener
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.google.android.gms.ads.LoadAdError

class AdBanner(context: Context) {

    var context: Context = context
    var activity: Activity? = null
    private var onShowBanner: OnShowBanner? = null
    private final val TAG = "AdBanner"

    companion object {

        private var mInstance: AdBanner? = null

        fun getInstance(context: Activity): AdBanner {
            return mInstance ?: synchronized(this) {
                mInstance ?: AdBanner(context.applicationContext).also { mInstance = it }
            }
        }
    }


    @Composable
    fun ShowBannerAd(activity: Activity, onShowBanner: OnShowBanner) {
        this.onShowBanner = onShowBanner
        this.activity = activity

        if (Constant.AD_SHOW) {
            BannerContainer(activity = activity, onShowBanner = onShowBanner)
        } else {
            onShowBanner(onShowBanner, false)
        }
    }


}

fun onShowBanner(onShowBanner: OnShowBanner, boolean: Boolean) {
    onShowBanner.show(boolean)
}

@Composable
fun BannerContainer(activity: Activity, onShowBanner: OnShowBanner) {
    Box(Modifier.fillMaxWidth().height(60.dp), contentAlignment = Alignment.Center, ) {
        AndroidView(factory = {
            val adView = AdView(activity,Constant.FACEBOOK_BANNER_AD_ID, AdSize.BANNER_HEIGHT_50)


           val adListener = object : AdListener {
               override fun onError(p0: Ad?, p1: AdError?) {
                   onShowBanner(onShowBanner, false)
               }

               override fun onAdLoaded(p0: Ad?) {
                   onShowBanner(onShowBanner, true)
               }

               override fun onAdClicked(p0: Ad?) {
                   TODO("Not yet implemented")
               }

               override fun onLoggingImpression(p0: Ad?) {
                   TODO("Not yet implemented")
               }

           }
            adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build())

            adView
        }, update = {})
    }
}
