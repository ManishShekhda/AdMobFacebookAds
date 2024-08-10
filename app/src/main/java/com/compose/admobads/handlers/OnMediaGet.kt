package com.compose.admobads.handlers

import com.facebook.ads.MediaView

interface OnMediaGet {
    fun show(media: MediaView)
}