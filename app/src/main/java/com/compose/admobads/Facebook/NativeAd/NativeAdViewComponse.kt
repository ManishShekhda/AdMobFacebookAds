package com.compose.admobads.Facebook.NativeAd

import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.NativeAdLayout


@Composable
fun NativeAdViewComponse(
    modifier: Modifier = Modifier, content: @Composable (nativeAdView: NativeAdLayout) -> Unit
) = AndroidView(modifier = modifier, factory = {
    NativeAdLayout(it)
}, update = {
    val componseView = ComposeView(it.context)
    it.removeAllViews()
    it.addView(componseView)
    componseView.setContent { content(it) }

})


@Composable
fun NativeAdMediaView(
    modifier: Modifier,
    onMediaGet: MediaView
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(factory = { context ->
            onMediaGet
        })
    }
}

@Composable
fun NativeAdClickView(
    modifier: Modifier,
    onMediaGet: View
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(factory = { context ->
            onMediaGet
        })
    }
}

@Composable
fun AdOptionsView(
    modifier: Modifier,
    onMediaGet: LinearLayout
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(factory = { context ->
            onMediaGet
        })
    }
}

@Composable
fun NativeAdMediaViewLarge(
    modifier: Modifier,
    onMediaGet: MediaView
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AndroidView(factory = { context ->
            onMediaGet
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