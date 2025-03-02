package com.dropbearsoft.kmpmsaldemo.auth

data class MsalConfig(
    val clientId: String,
    val authority: String,
    val redirectUri: String,
    val scopes: String
)