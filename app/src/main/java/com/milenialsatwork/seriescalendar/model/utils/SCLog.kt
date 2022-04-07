package com.milenialsatwork.seriescalendar.model.utils

import android.util.Log
import java.lang.Exception

class SCLog {
    companion object {
        val TAG: String = "SeriesCalendar"
        fun d(classTag: String, content: String) {
            // TODO: Convert to Inline Function hereafter
            Log.d(TAG, "[$classTag]: $content")
        }

        fun w(classTag: String, content: String) {
            Log.w(TAG, "[$classTag]: $content")
        }

        fun e(classTag: String, content: String) {
            Log.e(TAG, "[$classTag]: $content")
        }

        fun e(classTag: String, content: String, exception: Exception) {
            Log.e(TAG, "[$classTag]: $content", exception)
        }

        val isLoggableD = Log.isLoggable(TAG, Log.DEBUG)
    }


}