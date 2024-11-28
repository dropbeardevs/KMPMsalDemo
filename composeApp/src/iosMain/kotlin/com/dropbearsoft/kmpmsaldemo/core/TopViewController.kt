package com.dropbearsoft.kmpmsaldemo.core

import platform.UIKit.*
import platform.UIKit.UIWindow

fun getTopViewController(): UIViewController {
    val sharedApplication = UIApplication.sharedApplication

    if ((UIDevice.currentDevice.systemVersion.toDoubleOrNull() ?: 0.0) >= 13.0) {
        val windowScene = sharedApplication.connectedScenes
            .filterIsInstance<UIWindowScene>()
            .firstOrNull { it.activationState == UISceneActivationStateForegroundActive }

        val keyWindow = windowScene?.windows?.firstOrNull { (it as? UIWindow)?.isKeyWindow() == true } as? UIWindow

        return keyWindow?.rootViewController
            ?: throw IllegalStateException("Main UIViewController not found")
    } else {
        val keyWindow = sharedApplication.keyWindow
        return keyWindow?.rootViewController
            ?: throw IllegalStateException("Main UIViewController not found")
    }
}