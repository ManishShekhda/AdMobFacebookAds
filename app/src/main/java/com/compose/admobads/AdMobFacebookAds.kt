package com.compose.admobads

import android.content.Context
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds

class AdMobFacebookAds(context: Context) {

    companion object {

        private var mInstance: AdMobFacebookAds? = null

        fun initialize(context: Context): AdMobFacebookAds {
            MobileAds.initialize(context) {}
            AudienceNetworkAds.initialize(context)
            return mInstance ?: synchronized(this) {
                mInstance ?: AdMobFacebookAds(context.applicationContext).also { mInstance = it }
            }
        }

    }


    fun setTest(boolean: Boolean) {
        Constant.IS_TEST = boolean
    }

    fun setAdsShow(boolean: Boolean) {
        Constant.AD_SHOW = boolean
    }

    fun setAdmobInterstitialAdId(id: String) {
        Constant.ADMOB_INTERSTITIAL_AD_ID = id
    }

    fun setAdmobNativeAdId(id: String) {
        Constant.ADMOB_NATIVE_AD_ID = id
    }

    fun setAdmobOpenAdId(id: String) {
        Constant.ADMOB_APP_OPEN_AD_ID = id
    }

    fun setAdmobBannerAdId(id: String) {
        Constant.ADMOB_BANNER_AD_ID = id
    }

    fun setFacebookInterstitialAdId(id: String) {
        Constant.FACEBOOK_INTERSTITIAL_AD_ID = id
    }

    fun setFacebookNativeAdId(id: String) {
        Constant.FACEBOOK_NATIVE_AD_ID = id
    }

    fun setFacebookNativeBannerAdId(id: String) {
        Constant.FACEBOOK_NATIVE_BANNER_AD_ID = id
    }

    fun setFacebookBannerAdId(id: String) {
        Constant.FACEBOOK_BANNER_AD_ID = id
    }

}