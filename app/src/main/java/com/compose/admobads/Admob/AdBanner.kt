package com.compose.admobads.Admob

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
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
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
    Box(
        Modifier
            .fillMaxWidth()
            .height(60.dp), contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = {
            val adView = AdView(activity)
            adView.adUnitId = if (Constant.IS_TEST) {
                "ca-app-pub-3940256099942544/6300978111"
            } else {
                Constant.ADMOB_BANNER_AD_ID
            }
            adView.setAdSize(AdSize.BANNER)

            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)

            adView.adListener = object : AdListener() {
                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    onShowBanner(onShowBanner, true)
                }


                override fun onAdOpened() {
                    super.onAdOpened()
                }

                override fun onAdSwipeGestureClicked() {
                    super.onAdSwipeGestureClicked()
                }
            }
            adView
        }, update = {})
    }
}
