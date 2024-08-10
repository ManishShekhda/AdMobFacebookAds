package com.compose.admobads.Facebook.NativeAd

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.facebook.ads.AdOptionsView
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeBannerAd

@Composable
fun ShowNativeAdView(nativeAd: NativeAd?, activity: Activity) {
    Log.d("ShowNativeAdView", "nativeAd: null")
    if (nativeAd != null) {

        var arrayList: ArrayList<View> = ArrayList<View>()

        NativeAdViewComponse { nativeAdView ->
            val mediaViewLarge = MediaView(activity)
            val mediaViewIcon = MediaView(activity)
            val views = View(activity)
            arrayList.add(mediaViewLarge)
            arrayList.add(mediaViewIcon)
            arrayList.add(views)

            val linearLayout = LinearLayout(activity)
            val adOptionsView = AdOptionsView(activity,nativeAd,nativeAdView)
            linearLayout.removeAllViews()
            linearLayout.addView(adOptionsView)

            Log.d("ShowNativeAdView", "ShowNativeAdView: ")

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    ) {
                        NativeAdViewLayout(getView = {
                            it.removeAllViews()
                        }) {
                            NativeAdMediaView(
                                modifier = Modifier.size(55.dp),
                                mediaViewIcon
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 5.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = nativeAd.adHeadline ?: "-",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    color = Color.Black,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    ),
                                    maxLines = 1
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                NativeAdViewLayout(getView = {

                                }) {
                                    Text(
                                        text = nativeAd.adBodyText ?: "-",
                                        color = Color.Black,
                                        fontSize = 12.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Start,
                                        style = TextStyle(
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        ),
                                        maxLines = 2
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        AdOptionsView(modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight() , linearLayout )
                    }


                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(vertical = 5.dp)
                    ) {
                        NativeAdMediaViewLarge(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            mediaViewLarge
                        )
                    }





                    NativeAdViewLayout(getView = {

                    }) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center,
                        ) {

                            Text(
                                nativeAd.adCallToAction ?: "-",
                                modifier = Modifier,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                            NativeAdClickView(modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp) , views )

                        }
                    }


                    Log.d("TAG", "ShowNativeAdView: " + nativeAdView + " == " + mediaViewIcon)
                    nativeAd.registerViewForInteraction(
                        nativeAdView,
                        mediaViewLarge,
                        mediaViewIcon,
                        arrayList
                    )


                }
            }


        }
    }
}

@Composable
fun ShowNativeAdViewSmall(nativeAd: NativeBannerAd?, activity: Activity) {
    Log.d("ShowNativeAdView", "nativeAd: null")
    if (nativeAd != null) {

        var arrayList: ArrayList<View> = ArrayList<View>()

        NativeAdViewComponse { nativeAdView ->
            val mediaViewIcon = MediaView(activity)
            val views = View(activity)
            arrayList.add(mediaViewIcon)
            arrayList.add(views)

            val linearLayout = LinearLayout(activity)
            val adOptionsView = AdOptionsView(activity,nativeAd,nativeAdView)
            linearLayout.removeAllViews()
            linearLayout.addView(adOptionsView)

            Log.d("ShowNativeAdView", "ShowNativeAdView: ")

            Box(Modifier.fillMaxWidth()) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    ) {
                        NativeAdViewLayout(getView = {
                            it.removeAllViews()
                        }) {
                            NativeAdMediaView(
                                modifier = Modifier.size(55.dp),
                                mediaViewIcon
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 5.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = nativeAd.adHeadline ?: "-",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    color = Color.Black,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    ),
                                    maxLines = 1
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                NativeAdViewLayout(getView = {

                                }) {
                                    Text(
                                        text = nativeAd.adBodyText ?: "-",
                                        color = Color.Black,
                                        fontSize = 12.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Start,
                                        style = TextStyle(
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        ),
                                        maxLines = 2
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Column(modifier = Modifier.wrapContentWidth().fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.End) {

                            AdOptionsView(modifier = Modifier.wrapContentWidth().wrapContentHeight() , linearLayout )
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .height(30.dp)
                                    .background(
                                        color = Color.Blue,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(horizontal = 5.dp),
                                contentAlignment = Alignment.Center,
                            ) {

                                Text(
                                    nativeAd.adCallToAction ?: "-",
                                    modifier = Modifier,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    style = TextStyle()
                                )
                                NativeAdClickView(modifier = Modifier
                                    .width(100.dp)
                                    .height(45.dp) , views )
                            }
                        }
                    }
                }
                nativeAd.registerViewForInteraction(
                    nativeAdView,
                    mediaViewIcon,
                    arrayList
                )
            }
        }
    }
}






