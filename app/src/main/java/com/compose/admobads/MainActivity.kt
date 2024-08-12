package com.compose.admobads

import android.media.tv.AdRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.compose.admobads.ui.theme.AdMobAdsTheme
import com.compose.ads.admobfacebookads.AdMobFacebookAds
import com.compose.ads.admobfacebookads.AdType
import com.compose.ads.admobfacebookads.AdsShow
import com.compose.ads.admobfacebookads.NativeType
import com.compose.ads.admobfacebookads.handlers.OnShow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdMobAdsTheme {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    val context = LocalContext.current
                    AdMobFacebookAds.initialize(context).setAdsShow(true).setTest(true).setAdType(AdType.ADMOB).setAdmobInterstitialAdId("").setAdmobNativeAdId("").setAdmobBannerAdId("")


                    AdsShow.getInstance(context).ShowNativeAd(
                        activity = this@MainActivity,
                        nativeType = NativeType.NATIVE_BIG
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .weight(1f), contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            AdsShow.getInstance(context).ShowInterstitial(object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }, this@MainActivity)
                        }) {
                            Text(text = "Click")
                        }
                    }

                    AdsShow.getInstance(context).ShowNativeAd(
                        activity = this@MainActivity,
                        nativeType = NativeType.NATIVE_BANNER
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}