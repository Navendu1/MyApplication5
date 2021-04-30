package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class opinionrewards : AppCompatActivity() {

    lateinit var mAdView4 : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinionrewards)

        val adView = AdView(this)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"


        MobileAds.initialize(this) {}

        mAdView4 = findViewById(R.id.adView4)
        val adRequest = AdRequest.Builder().build()
        mAdView4.loadAd(adRequest)
    }
}