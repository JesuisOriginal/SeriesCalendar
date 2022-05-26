package com.milenialsatwork.seriescalendar.model.internetUtils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64.decode
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import java.io.IOException
import java.net.URL

class ImageUtility {
    companion object {
        fun getBitMapFromURL(url: String): Bitmap? {
            var bitmap: Bitmap? = null
            try {
                val url = URL(url)
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (e: IOException) {
                SCLog.e(TAG, "getBitmapFromUrl: IOException: ", e)
            }
            return bitmap
        }
        fun encodeImageFromUrl(url: String): String {
               return getBitMapFromURL(url).toString()
        }
        fun decodeImageFromString(imageStringMap: String): Bitmap {
            val imageBytes = decode(imageStringMap, 0)
            return BitmapFactory.decodeByteArray(imageBytes, 0 , imageBytes.size)
        }
        private var TAG = "ImageUtility"
    }
}