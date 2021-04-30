package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class gatstartthentextv : AppCompatActivity() {
    var networkChangeListener: MyReceiver = MyReceiver()
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "getstart"

    lateinit var  button: Button
    lateinit var textView: TextView
    lateinit var mAdView5 : AdView
    lateinit var mAdView6 : AdView
//    lateinit var  textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gatstartthentextv)

    val adView = AdView(this)
    MobileAds.initialize(this) {}

    mAdView5 = findViewById(R.id.adViewgattex)
    mAdView6 = findViewById(R.id.adViewgettex1)
    val adRequest = AdRequest.Builder().build()
    mAdView5.loadAd(adRequest)
    mAdView6.loadAd(adRequest)

    adView.adSize = AdSize.LARGE_BANNER

    adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        button = findViewById(R.id.button4)
        textView = findViewById(R.id.textView11)



    val name =intent.getStringExtra("name")
    this.textView.text = "Result \n $name$ \n Please waiting for 72-120 hours, \n if not working please try again. Actually a lot of user Try To Redeem code, so diamond sending process slow. "

        button.setOnClickListener {
            if (mInterstitialAd != null) {

                mInterstitialAd?.show(this)
            } else {

                intent = Intent(applicationContext, lestActivity::class.java)
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

                        intent = Intent(applicationContext,lestActivity::class.java)


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