package com.compose.admobads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.compose.admobads.Facebook.NativeAd.MainViewModel
import com.compose.admobads.handlers.OnShow
import com.compose.admobads.ui.theme.AdMobAdsTheme

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            AdMobAdsTheme {
                Column(Modifier.fillMaxSize()) {
                    var context = LocalContext.current
                    AdsShow.getInstance(context).ShowNative(mainViewModel, activity = this@MainActivity,2)
                    Box(Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) {

                        Button(onClick = {
                            AdsShow.getInstance(context).ShowInterstitial(object : OnShow {
                                override fun show(show: Boolean) {

                                }
                            }, this@MainActivity)
                        }) {
                            Text(text = "Click")
                        }
                    }
                    AdsShow.getInstance(context).ShowNative(mainViewModel, activity = this@MainActivity,1)
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

    }
}