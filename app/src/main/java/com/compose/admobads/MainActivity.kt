package com.compose.admobads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.admobads.Facebook.NativeAd.FacebookAdNative
import com.compose.admobads.Facebook.NativeAd.NativeAdViewLayout
import com.compose.admobads.handlers.OnShow
import com.compose.admobads.ui.theme.AdMobAdsTheme

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
                    AdMobFacebookAds.initialize(context)
                    FacebookAdNative.getInstance(context).ShowFacebookNativeBig(
                        activity = this@MainActivity,
                        onShow = object : OnShow {
                            override fun show(show: Boolean) {

                            }
                        }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdMobAdsTheme {
//        NativeAdViewComponse { nativeAdView ->

//            Column(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(5.dp)
//                    .clip(RoundedCornerShape(15.dp))
//                    .background(Color.White)
//                    .padding(5.dp)
//            ) {
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(55.dp)
//                ) {
//                    NativeAdViewLayout(getView = {
//                        it.removeAllViews()
//                    }) {
//                        Box(
//                            Modifier
//                                .size(55.dp)
//                                .background(Color.Gray)) {
//
//                        }
//                    }
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                            .padding(start = 5.dp)
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(start = 5.dp)
//                        ) {
//                            NativeAdViewLayout(getView = {
//
//                            }) {
//                                Text(
//                                    text =  "Head Line, this is facebook and admob test ad demo",
//                                    fontSize = 14.sp,
//                                    color = Color.Black,
//                                    fontWeight = FontWeight.Bold,
//                                    overflow = TextOverflow.Ellipsis,
//                                    style = TextStyle(
//                                        platformStyle = PlatformTextStyle(
//                                            includeFontPadding = false
//                                        )
//                                    ),
//                                    maxLines = 1,
//
//                                    )
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(3.dp))
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(start = 5.dp)
//                        ) {
//
//                            NativeAdViewLayout(getView = {
//
//                            }) {
//                                Text(
//                                    text =  "this is facebook and admob test ad demo, this is facebook and admob test ad demo., this is facebook and admob test ad demo.",
//                                    color = Color.Black,
//                                    fontSize = 12.sp,
//                                    textAlign = TextAlign.Start,
//                                    overflow = TextOverflow.Ellipsis,
//                                    style = TextStyle(
//                                        platformStyle = PlatformTextStyle(
//                                            includeFontPadding = false
//                                        )
//                                    ),
//                                    maxLines = 2
//                                )
//                            }
//
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.width(5.dp))
//                    Box(modifier = Modifier
//                        .width(50.dp)
//                        .height(30.dp)
//                        .background(Color.DarkGray))
//                }
//
//
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(150.dp)
//                        .padding(vertical = 5.dp)
//                        .background(Color.Gray)
//                ) {
//
//                }
//
//                NativeAdViewLayout(getView = {
//
//                }) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(45.dp)
//                            .background(color = Color.Blue, shape = RoundedCornerShape(10.dp)),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                        Text(
//                            "-",
//                            color = Color.White,
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//
//                }


//            }


//        }


        Box(Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .padding(5.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Box(modifier = Modifier
                        .size(60.dp)
                        .background(Color.Gray))

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
                                .weight(1f)
                                .padding(start = 5.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Head Line, this is facebook and admob test ad demo",
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
                                    text = "this is facebook and admob test ad demo, this is facebook and admob test ad demo., this is facebook and admob test ad demo.",
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
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(25.dp)
                                .background(Color.DarkGray)
                        )

                        NativeAdViewLayout(getView = {

                        }) {

                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .height(30.dp)
                                    .background(
                                        color = Color.Blue,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(horizontal = 10.dp),
                                contentAlignment = Alignment.Center,
                            ) {

                                Text(
                                    "Install Now",
                                    modifier = Modifier,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
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
}