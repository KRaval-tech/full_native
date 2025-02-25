package com.example.full_native

import com.google.android.gms.ads.MediaAspectRatio
import com.google.android.gms.ads.nativead.NativeAdOptions
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.googlemobileads.GoogleMobileAdsPlugin

class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // Native Ad Factory Register kar rahe hain
        GoogleMobileAdsPlugin.registerNativeAdFactory(
            flutterEngine, "full_native", CustomNativeAdFactory(context)
        )

        // NativeAdOptions ko portrait aspect ratio ke saath register karna
        val adOptions = NativeAdOptions.Builder()
            .setMediaAspectRatio(MediaAspectRatio.PORTRAIT) // Portrait mode enable
            .build()
    }

    override fun cleanUpFlutterEngine(flutterEngine: FlutterEngine) {
        super.cleanUpFlutterEngine(flutterEngine)
        GoogleMobileAdsPlugin.unregisterNativeAdFactory(
            flutterEngine, "full_native"
        )
    }
}
