package com.compose.ads.admobfacebookads.handlers

import com.facebook.ads.MediaView

interface OnMediaGet {
    fun show(media: MediaView)
}