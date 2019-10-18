package com.marvel.ledannyyang.util

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress

object ConnectionUtils {

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}