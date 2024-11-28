package com.dropbearsoft.kmpmsaldemo.auth

fun getMsalConfig(): MsalConfig {
    val msalConfig = MsalConfig(
        clientId = "",
        authority = "https://login.microsoftonline.com/",
        redirectUri = ""
    )

    return msalConfig
}