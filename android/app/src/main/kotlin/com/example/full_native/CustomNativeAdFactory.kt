//package com.example.full_native
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import com.google.android.gms.ads.nativead.MediaView
//import com.google.android.gms.ads.nativead.NativeAd
//import com.google.android.gms.ads.nativead.NativeAdView
//import io.flutter.plugins.googlemobileads.GoogleMobileAdsPlugin
//import org.w3c.dom.Text
//
//class CustomNativeAdFactory(var context: Context) : GoogleMobileAdsPlugin.NativeAdFactory {
//    override fun createNativeAd(
//        nativeAd: NativeAd?,
//        customOptions: MutableMap<String, Any>?
//    ): NativeAdView {
//        val adView = LayoutInflater.from(context).inflate(R.layout.native_full,null) as NativeAdView
//        val adOptions = NativeAdOptions.Builder()
//            .setMediaAspectRatio(MediaAspectRatio.PORTRAIT)
//            .build()
//
////        adView.findViewById<NativeAdView>(R.id.native_ad_view).apply {
////            mediaView = findViewById<MediaView>(R.id.ad_media)
////            headlineView = findViewById<TextView>(R.id.ad_headline)
////            bodyView = findViewById<TextView>(R.id.ad_body)
////            callToActionView = findViewById<Button>(R.id.ad_call_to_action)
////            iconView = findViewById<ImageView>(R.id.ad_app_icon)
////        }
////
////        (adView.headlineView as? TextView)?.text = nativeAd?.headline
////        (adView.bodyView as? TextView)?.text = nativeAd?.body
////        (adView.callToActionView as? Button)?.text = nativeAd?.callToAction
////        (adView.iconView as? ImageView)?.setImageDrawable(nativeAd?.icon?.drawable)
////        adView.mediaView?.mediaContent = nativeAd?.mediaContent
////
////        nativeAd?.let { adView.setNativeAd(it) }
////        return adView
//
//        // Set MediaView
//        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
//        adView.mediaView = mediaView
//        //mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//
//        // Set Text Fields
//        adView.headlineView = adView.findViewById<TextView>(R.id.ad_headline).apply { text = nativeAd?.headline }
//        adView.bodyView = adView.findViewById<TextView>(R.id.ad_body).apply { text = nativeAd?.body ?: "" }
//
//        // Set Icon (if available)
//        val iconView = adView.findViewById<ImageView>(R.id.ad_app_icon)
//        if (nativeAd?.icon != null) {
//            iconView.setImageDrawable(nativeAd.icon?.drawable)
//            adView.iconView = iconView
//        } else {
//            iconView.visibility = View.GONE
//        }
//        // Set CTA Button
//        val ctaButton = adView.findViewById<Button>(R.id.ad_call_to_action)
//        ctaButton.text = nativeAd?.callToAction
//        adView.callToActionView = ctaButton
//
//        // Assign the NativeAd object to the NativeAdView
//        nativeAd?.let { adView.setNativeAd(it) }
//
//        return adView
//    }
//}


package com.example.full_native

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import io.flutter.plugins.googlemobileads.GoogleMobileAdsPlugin

class CustomNativeAdFactory(var context: Context) : GoogleMobileAdsPlugin.NativeAdFactory {
    override fun createNativeAd(
        nativeAd: NativeAd?,
        customOptions: MutableMap<String, Any>?
    ): NativeAdView {
        val adView = LayoutInflater.from(context).inflate(R.layout.native_full, null) as NativeAdView
//        val adOptions = NativeAdOptions.Builder()
//            .setMediaAspectRatio(MediaAspectRatio.PORTRAIT)
//            .build()
        // Set MediaView
        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
        adView.mediaView = mediaView
//        mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)

        // Set Ad Attribution Label
        adView.findViewById<TextView>(R.id.ad_attribution).apply {
            text = "Ad"
            visibility = View.VISIBLE
        }

        // Set Text Fields
        adView.headlineView = adView.findViewById<TextView>(R.id.ad_headline).apply {
            text = nativeAd?.headline
        }
        adView.bodyView = adView.findViewById<TextView>(R.id.ad_body).apply {
            text = nativeAd?.body ?: ""
        }

        // Set Icon (if available) with circular border
        val iconView = adView.findViewById<ImageView>(R.id.ad_app_icon)
        if (nativeAd?.icon != null) {
            iconView.setImageDrawable(nativeAd.icon?.drawable)
            adView.iconView = iconView
        } else {
            iconView.visibility = View.GONE
        }

        // Set CTA Button
        val ctaButton = adView.findViewById<Button>(R.id.ad_call_to_action)
        ctaButton.text = nativeAd?.callToAction
        adView.callToActionView = ctaButton

        // Assign the NativeAd object to the NativeAdView
        nativeAd?.let { adView.setNativeAd(it) }

        return adView
    }
}
