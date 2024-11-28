package com.dropbearsoft.kmpmsaldemo.di

import com.dropbearsoft.kmpmsaldemo.auth.AndroidMsalAuth
import com.dropbearsoft.kmpmsaldemo.auth.MsalAuth
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<MsalAuth> { AndroidMsalAuth() }
}