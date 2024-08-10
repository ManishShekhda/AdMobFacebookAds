package com.compose.admobads.Admob.NativeAd

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.android.gms.ads.MediaContent
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView


@Composable
fun NativeAdViewComponse(
    modifier: Modifier = Modifier, content: @Composable (nativeAdView: NativeAdView) -> Unit
) = AndroidView(modifier = modifier, factory = {
    NativeAdView(it)
}, update = {
    val componseView = ComposeView(it.context)
    it.removeAllViews()
    it.addView(componseView)
    componseView.setContent { content(it) }

})


@Composable
fun NativeAdMediaView(
    mediaContent: MediaContent,
    nativeAd: NativeAd,
    scalType: ImageView.ScaleType
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(factory = { context ->
            val view = NativeAdView(context)
            val mediaView = MediaView(context)
            view.mediaView = mediaView
            view.mediaView!!.mediaContent = mediaContent
            view.mediaView!!.setImageScaleType(scalType)
            view.setNativeAd(nativeAd)
            view.addView(mediaView)

            view
        })
    }
}

@Composable
fun NativeAdImageView(
    modifier: Modifier = Modifier,
    drawable: Drawable?,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) = Image(
    painter = rememberDrawablePainter(drawable = drawable),
    contentDescription = contentDescription,
    modifier = modifier,
    colorFilter = colorFilter,
    contentScale = contentScale,
    alignment = alignment,
    alpha = alpha
)


@Composable
fun NativeAdViewLayout(
    getView: (ComposeView) -> Unit, content: @Composable () -> Unit
) = AndroidView(factory = { ComposeView(it) }, update = {
    it.setContent(content)
    getView(it)
}
)