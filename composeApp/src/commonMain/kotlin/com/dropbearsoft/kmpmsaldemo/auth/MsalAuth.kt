package com.dropbearsoft.kmpmsaldemo.auth

interface MsalAuth {
    suspend fun getToken(): String
}