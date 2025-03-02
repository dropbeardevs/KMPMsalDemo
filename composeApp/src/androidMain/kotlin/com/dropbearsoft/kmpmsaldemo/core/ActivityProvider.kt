package com.dropbearsoft.kmpmsaldemo.core

import android.app.Activity
import java.lang.ref.WeakReference

object ActivityProvider {
    private var currentActivity: WeakReference<Activity>? = null

    fun setCurrentActivity(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    fun getCurrentActivity(): Activity? {
        return currentActivity?.get()
    }
}