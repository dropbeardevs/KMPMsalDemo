import SwiftUI
import composeApp

@main
struct iOSApp: App {

    // KMM - Koin Call
    init() {
        // TODO: Create list of Swift modules to send to Koin
        HelperKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}