package com.dropbearsoft.kmpmsaldemo.auth

import android.app.Activity
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.microsoft.identity.*
import com.dropbearsoft.kmpmsaldemo.R
import com.dropbearsoft.kmpmsaldemo.core.ActivityProvider
import com.dropbearsoft.kmpmsaldemo.utils.decodeJwt
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import org.koin.java.KoinJavaComponent.getKoin


class AndroidMsalAuth() : MsalAuth {
    private val context: Context = getKoin().get()
    override suspend fun getToken(): String {
        val application = createMSALApplication()

        val activity = ActivityProvider.getCurrentActivity()

        val scopes = listOf("User.Read")

        var token = ""

        try {

            if (application == null) {
                println("application is null")
                return "Invalid Token"
            }

            if (activity == null) {
                println("activity is null")
                return "Invalid Token"
            }

            val interactiveCallback = object : AuthenticationCallback {
                override fun onSuccess(result: IAuthenticationResult) {
                    token = result.accessToken
                    val accountIdentifier = result.account.id
                    val claimsJson = decodeJwt(token).toString()
                    println("Access Token: $token")
                    println("Account Identifier: $accountIdentifier")
                    println("Claims: $claimsJson")
                }

                override fun onError(exception: MsalException) {
                    println("Error acquiring token: ${exception.localizedMessage}")
                }

                override fun onCancel() {
                    println("User cancelled logon")
                }
            }

            application.acquireToken(activity, scopes.toTypedArray(), interactiveCallback)


        } catch (ex: Exception) {
            println(ex.message)
        }



        return token
    }

    private suspend fun createMSALApplication(): ISingleAccountPublicClientApplication? {

        val msalConfig = getMsalConfig()

        var mSingleAccountApp: ISingleAccountPublicClientApplication? = null

        val listener = object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
            override fun onCreated(application: ISingleAccountPublicClientApplication) {
                mSingleAccountApp = application
                println("MSALPublicClientApplication initialized successfully")
            }

            override fun onError(exception: MsalException) {
                println("Error occurred: ${exception.localizedMessage}")
            }
        }

        PublicClientApplication.createSingleAccountPublicClientApplication(
            context,
            R.raw.auth_config_single_account,
            listener
        )

        return mSingleAccountApp
    }
}