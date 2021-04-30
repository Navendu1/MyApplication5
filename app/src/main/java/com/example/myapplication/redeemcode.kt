package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class redeemcode : AppCompatActivity() {
    lateinit var mAdView10 : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redeemcode)


        val adView = AdView(this)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"


        MobileAds.initialize(this) {}
        mAdView10 = findViewById(R.id.adView10)
        val adRequest = AdRequest.Builder().build()
        mAdView10.loadAd(adRequest)
    }
}