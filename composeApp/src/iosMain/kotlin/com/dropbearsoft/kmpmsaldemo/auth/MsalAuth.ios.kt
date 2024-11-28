package com.dropbearsoft.kmpmsaldemo.auth

import cocoapods.MSAL.*
import kotlinx.cinterop.ExperimentalForeignApi
import com.dropbearsoft.kmpmsaldemo.core.getTopViewController
import kotlinx.cinterop.*
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
class IosMsalAuth() : MsalAuth {
    override suspend fun getToken(): String {

        var token = ""

        try {
            val viewController = getTopViewController()
            val scopes = listOf("User.Read")
            val application = createMSALApplication()

            if (application == null) {
                println("application is null")
                return "Invalid Token"
            }

            val webParameters = MSALWebviewParameters(authPresentationViewController = viewController)
            val interactiveParams = MSALInteractiveTokenParameters(scopes = scopes, webviewParameters = webParameters)

            application.acquireTokenWithParameters(interactiveParams) { result, error ->
                if (error == null && result != null) {
                    token = result.accessToken
                    val accountIdentifier = result.account.identifier
                    println("Access Token: $token")
                    println("Account Identifier: $accountIdentifier")
                } else {
                    println("Error acquiring token: ${error?.localizedDescription}")
                }
            }
        } catch (ex: Exception) {
            println(ex.message)
        }
        return token
    }
}

//@OptIn(ExperimentalForeignApi::class)
//fun getAccounts(application: MSALPublicClientApplication) {
//    application.getAccountsWithCompletion { accounts, error ->
//        if (error == null) {
//            accounts?.forEach { account ->
//                println("Account Identifier: ${account.identifier}")
//            }
//        } else {
//            println("Error retrieving accounts: ${error?.localizedDescription}")
//        }
//    }
//}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

fun createMSALApplication(): MSALPublicClientApplication? {

    val msalConfig =
        MsalConfig(
            clientId = "",
            authority = "https://login.microsoftonline.com/",
            redirectUri = ""
        )

    memScoped {
        try {
            // Allocate space for NSError pointer
            val nsErrorPtr = alloc<ObjCObjectVar<NSError?>>()

            val authorityUrl = NSURL(string = msalConfig.authority)

            val authority = MSALAuthority.authorityWithURL(authorityUrl, error = null)

            val config = MSALPublicClientApplicationConfig(
                clientId = msalConfig.clientId,
                authority = authority,
                redirectUri = msalConfig.redirectUri
            )

            val application = MSALPublicClientApplication(config, nsErrorPtr.ptr)

            // Check if an error occurred
            val nsError = nsErrorPtr.value
            if (nsError != null) {
                println("Error occurred: ${nsError.localizedDescription}")
            } else {
                println("MSALPublicClientApplication initialized successfully")
            }

            return application

        } catch (ex: Exception) {
            println(ex.message)
        }

        return null

    }
}