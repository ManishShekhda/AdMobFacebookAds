package com.compose.admobads.Facebook.NativeAd

import android.widget.LinearLayout
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.MediaView

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NativeAdViewSection(adType: Int, nativeAdState: AdState, rememberNativeAdState: NativeAdState?) {

    rememberNativeAdState?.let {
        val nativeAd by it.nativeAd.observeAsState()
        AnimatedContent(targetState = nativeAdState.isSuccess, label = "") { success ->
            if (success) {
                // NativeADView is Here
                Column(modifier = Modifier.wrapContentSize()){
                    if (adType == 1) {
//                        ShowSmallNativeAdView(nativeAd = nativeAd)
                    }else{
                        ShowNativeAdView(nativeAd = nativeAd)
                    }

                }
            } else {
//                Box(modifier = Modifier.size(20.dp)) {
//                    CircularProgressIndicator(color = Color.Blue)
//                }
            }

        }
    }

}

@Composable
fun NativeAdViewComponse(
    modifier: Modifier = Modifier, content: @Composable (nativeAdView: LinearLayout) -> Unit
) = AndroidView(modifier = modifier, factory = {
    LinearLayout(it)
}, update = {
    val componseView = ComposeView(it.context)
    it.removeAllViews()
    it.addView(componseView)
    componseView.setContent { content(it) }

})


@Composable
fun NativeAdMediaView(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(factory = { context ->
            MediaView(context)
        })
    }
}


@Composable
fun NativeAdViewLayout(
    getView: (ComposeView) -> Unit, content: @Composable () -> Unit
) = AndroidView(factory = { ComposeView(it) }, update = {
    it.setContent(content)
    getView(it)
}
)