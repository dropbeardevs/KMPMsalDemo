package com.dropbearsoft.kmpmsaldemo.di

import org.koin.core.context.startKoin

// For Swift to use Koin DI, we need to create helpers,
// And then inject them into SwiftUI
//class GreetingHelper : KoinComponent {
//    private val greeting : Greeting by inject()
//    fun greet() : String = greeting.greeting()
//}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}