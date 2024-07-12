package com.compose.admobads

import android.app.Application
import com.compose.admobads.Admob.AppOpenManager
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
        RequestConfiguration.Builder().setTestDeviceIds(listOf("CEC07A14BACFCB6775973CA0053D6901"))
        AppOpenManager(this).AppOpenManager(this)
    }


}