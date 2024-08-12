package com.compose.ads.admobfacebookads

import android.content.Context
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds

open class AdMobFacebookAds() {

    companion object {

        private var mInstance: AdMobFacebookAds? = null

        fun initialize(context: Context): AdMobFacebookAds {
            MobileAds.initialize(context) {}
            AudienceNetworkAds.initialize(context)
            return mInstance ?: synchronized(this) {
                mInstance ?: AdMobFacebookAds().also { mInstance = it }
            }
        }

//        fun initialize(context: Context): Create {
//            MobileAds.initialize(context) {}
//            AudienceNetworkAds.initialize(context)
//            return Create(context)
//        }

    }

    fun setTest(boolean: Boolean): AdMobFacebookAds {
        Constant.IS_TEST = boolean
        return this
    }
    fun setAdType(adType: AdType): AdMobFacebookAds {
        Constant.AD_TYPE = adType
        return this
    }

    fun setAdsShow(boolean: Boolean): AdMobFacebookAds {
        Constant.AD_SHOW = boolean
        return this
    }

    fun setAdmobInterstitialAdId(id: String): AdMobFacebookAds {
        Constant.ADMOB_INTERSTITIAL_AD_ID = id
        return this
    }

    fun setAdmobNativeAdId(id: String): AdMobFacebookAds {
        Constant.ADMOB_NATIVE_AD_ID = id
        return this
    }

    fun setAdmobOpenAdId(id: String): AdMobFacebookAds {
        Constant.ADMOB_APP_OPEN_AD_ID = id
        return this
    }

    fun setAdmobBannerAdId(id: String): AdMobFacebookAds {
        Constant.ADMOB_BANNER_AD_ID = id
        return this
    }

    fun setFacebookInterstitialAdId(id: String): AdMobFacebookAds {
        Constant.FACEBOOK_INTERSTITIAL_AD_ID = id
        return this
    }

    fun setFacebookNativeAdId(id: String) : AdMobFacebookAds{
        Constant.FACEBOOK_NATIVE_AD_ID = id
        return this
    }

    fun setFacebookNativeBannerAdId(id: String) : AdMobFacebookAds{
        Constant.FACEBOOK_NATIVE_BANNER_AD_ID = id
        return this
    }

    fun setFacebookBannerAdId(id: String): AdMobFacebookAds {
        Constant.FACEBOOK_BANNER_AD_ID = id
        return this
    }

}