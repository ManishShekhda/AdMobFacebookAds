package com.compose.admobads

import android.app.Application
import com.compose.admobads.Admob.AppOpenManager
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.Arrays

class App : Application() {

    var appOpenAdManager: AppOpenManager? = null


    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(
            this
        ) { }
        AudienceNetworkAds.initialize(this)
        AdSettings.addTestDevice("6f8b8a8a-5238-44b0-b11e-beea061276f6")
    }


}