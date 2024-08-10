package com.compose.admobads.Facebook.NativeAd

import android.content.Context
import android.widget.FrameLayout
import android.widget.Toast
import com.facebook.ads.MediaView

class MediaView(context: Context) : MediaView(context) {

    init {
        isClickable = true // Enable clickable
        // Set OnClickListener to handle clicks
        setOnClickListener {
            // Handle click event here
            Toast.makeText(context, "MediaView clicked", Toast.LENGTH_SHORT).show()
        }
    }
}