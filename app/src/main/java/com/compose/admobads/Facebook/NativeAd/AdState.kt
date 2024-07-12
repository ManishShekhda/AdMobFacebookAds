package com.compose.admobads.Facebook.NativeAd

data class AdState(
    val isSuccess :Boolean =  false,
    val isError:Boolean = false,
    val errorMessage :String? = null
)
