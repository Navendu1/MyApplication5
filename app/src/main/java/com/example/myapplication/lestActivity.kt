package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class lestActivity : AppCompatActivity() {
    var networkChangeListener: MyReceiver = MyReceiver()
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "lest"

    lateinit var  button: Button
    lateinit var button3: Button
    lateinit var mAdView7 : AdView
    lateinit var mAdView8 : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lest)
        val adView = AdView(this)
        MobileAds.initialize(this) {}

        mAdView7 = findViewById(R.id.adViewlest)
        mAdView8 = findViewById(R.id.adViewlest1)
        val adRequest = AdRequest.Builder().build()
        mAdView7.loadAd(adRequest)
        mAdView8.loadAd(adRequest)

        adView.adSize = AdSize.LARGE_BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        button = findViewById(R.id.button5)
        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.guidefire.freediamonds")

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        button.setOnClickListener {
            if (mInterstitialAd != null) {

                mInterstitialAd?.show(this)
            } else {
                intent = Intent(applicationContext, MainActivity2::class.java)
                startActivity(intent)

            }
        }
    }

    override fun onStart() {

        MobileAds.initialize(this)



        //add adMob
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {


                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {

                    override fun onAdDismissedFullScreenContent() {



//

                        intent = Intent(applicationContext,MainActivity2::class.java)


                        startActivity(intent)


                        Log.d(TAG, "Ad was dismissed.")
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        Log.d(TAG, "Ad failed to show.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        mInterstitialAd = null;
                    }

                }
            }
        })




        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener, filter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }


}