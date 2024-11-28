package com.dropbearsoft.kmpmsaldemo.auth

import cocoapods.MSAL.*
import kotlinx.cinterop.*
import platform.Foundation.*

data class MsalConfig(
    val clientId: String,
    val authority: String,
    val redirectUri: String,
)