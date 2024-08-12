package com.compose.ads.admobfacebookads

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import com.compose.ads.admobfacebookads.Admob.NativeAd.AdMobNativeAd
import com.compose.ads.admobfacebookads.Facebook.NativeAd.FacebookAdNative
import com.compose.ads.admobfacebookads.handlers.OnShow
import com.compose.ads.admobfacebookads.handlers.OnShowBanner
import com.compose.ads.admobfacebookads.handlers.OnShowInterstitial
import com.compose.ads.admobfacebookads.Facebook.AdBanner
import com.compose.ads.admobfacebookads.Facebook.AdInterstitial
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds

class AdsShow() {

    private var onShow: OnShow? = null

    companion object {

        private var mInstance: AdsShow? = null

        fun getInstance(context: Context): AdsShow {
            MobileAds.initialize(context) {}
            AudienceNetworkAds.initialize(context)
            return mInstance ?: synchronized(this) {
                mInstance ?: AdsShow().also { mInstance = it }
            }
        }
    }

    fun ShowInterstitial(onShow: OnShow, activity: Activity) {
        this.onShow = onShow

        if (Constant.AD_TYPE == AdType.ADMOB) {
            com.compose.ads.admobfacebookads.Admob.AdInterstitial.getInstance(activity)
                .ShowInterstitilAd(activity, object : OnShowInterstitial {
                    override fun show(show: Boolean) {
                        onShow(show)
                    }
                })
        } else if (Constant.AD_TYPE == AdType.FACEBOOK) {
            AdInterstitial.getInstance(activity).ShowInterstitilAd(
                activity = activity,
                onShowInterstitial = object : OnShowInterstitial {
                    override fun show(show: Boolean) {

                    }

                })
        }

    }

    @Composable
    fun ShowNativeAd(activity: Activity, nativeType: NativeType) {

        if (Constant.AD_SHOW) {
            if (Constant.AD_TYPE == AdType.ADMOB) {
                when (nativeType) {
                    NativeType.NATIVE_BIG -> {
                        AdMobNativeAd.getInstance(activity).ShowAdMobNativeBig(
                            activity = activity,
                            onShow = object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }

                    NativeType.NATIVE_SMALL -> {
                        AdMobNativeAd.getInstance(activity).ShowAdMobNativeSmall(
                            activity = activity,
                            onShow = object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }

                    else -> {
                        com.compose.ads.admobfacebookads.Admob.AdBanner.getInstance(activity).ShowBannerAd(
                            activity = activity,
                            onShowBanner = object : OnShowBanner {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }
                }
            } else if (Constant.AD_TYPE == AdType.FACEBOOK) {
                when (nativeType) {
                    NativeType.NATIVE_BIG -> {
                        FacebookAdNative.getInstance(activity).ShowFacebookNativeBig(
                            activity = activity,
                            onShow = object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }

                    NativeType.NATIVE_SMALL -> {
                        FacebookAdNative.getInstance(activity).ShowFacebookNativeSmall(
                            activity = activity,
                            onShow = object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }

                    else -> {
                        AdBanner.getInstance(activity).ShowBannerAd(
                            activity = activity,
                            onShowBanner = object : OnShowBanner {
                                override fun show(show: Boolean) {

                                }
                            }
                        )
                    }
                }
            }

        }

    }


    private fun onShow(boolean: Boolean) {
        if (onShow != null) {
            onShow?.show(boolean)
            onShow = null
        }
    }
}