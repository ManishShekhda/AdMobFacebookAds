package com.compose.ads.admobfacebookads.Admob.NativeAd

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun ShowAdmobNativeAd(nativeAd: NativeAd?) {

    if (nativeAd != null) {
        NativeAdViewComponse { nativeAdView ->
            nativeAdView.setNativeAd(nativeAd)
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
                        nativeAdView.iconView = it
                    }) {
                        NativeAdImageView(
                            drawable = nativeAd.icon?.drawable,
                            contentDescription = "Icon",
                            modifier = Modifier.size(55.dp)
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
                                .padding(start = 5.dp)
                        ) {
                            Row(
                                Modifier
                                    .wrapContentSize()
                                    .background(Color.Blue, shape = RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "Ad",
                                    fontSize = 10.sp,
                                    color = Color.White,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    ),
                                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            NativeAdViewLayout(getView = {
                                nativeAdView.headlineView = it
                            }) {
                                Text(
                                    text = nativeAd.headline ?: "-",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    overflow = TextOverflow.Ellipsis,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    ),
                                    maxLines = 1
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp)
                        ) {
                            NativeAdViewLayout(getView = {
                                nativeAdView.bodyView = it
                            }) {
                                Text(
                                    text = nativeAd.body ?: "-",
                                    color = Color.Black,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 12.sp,
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
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    nativeAd.mediaContent?.let { mediaContent ->
                        NativeAdMediaView(
                            nativeAd = nativeAd,
                            mediaContent = mediaContent,
                            scalType = ImageView.ScaleType.CENTER_CROP
                        )
                    }


                }

                NativeAdViewLayout(getView = {
                    nativeAdView.callToActionView = it
                }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(color = Color.Blue, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            nativeAd.callToAction ?: "-",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

            }
        }
    }
}


@Composable
fun ShowSmallNativeAdView(nativeAd: NativeAd?) {

    if (nativeAd != null) {
        NativeAdViewComponse { nativeAdView ->
            nativeAdView.setNativeAd(nativeAd)
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
                        .height(55.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NativeAdViewLayout(getView = {
                        nativeAdView.iconView = it
                    }) {
                        NativeAdImageView(
                            drawable = nativeAd.icon?.drawable,
                            contentDescription = "Icon",
                            modifier = Modifier.size(50.dp)
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
                                .padding(start = 5.dp)
                        ) {
                            Row(
                                Modifier
                                    .wrapContentSize()
                                    .background(Color.Blue, shape = RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "Ad",
                                    fontSize = 10.sp,
                                    color = Color.White,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    ),
                                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            com.compose.ads.admobfacebookads.Facebook.NativeAd.NativeAdViewLayout(
                                getView = {
                                    nativeAdView.headlineView = it
                                }) {
                                Text(
                                    text = nativeAd.headline ?: "-",
                                    fontSize = 14.sp,
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
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp)
                                .wrapContentSize()
                        ) {
                            com.compose.ads.admobfacebookads.Facebook.NativeAd.NativeAdViewLayout(
                                getView = {
                                    nativeAdView.bodyView = it
                                }) {
                                Text(
                                    text = nativeAd.body ?: "-",
                                    color = Color.Black,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 10.sp,
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

                    NativeAdViewLayout(getView = {
                        nativeAdView.callToActionView = it
                    }) {
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(30.dp)
                                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                nativeAd.callToAction ?: "-",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                style = TextStyle()
                            )
                        }

                    }
                }


            }
        }
    }
}



