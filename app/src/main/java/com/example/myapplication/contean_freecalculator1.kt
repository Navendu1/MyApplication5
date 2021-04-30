package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class contean_freecalculator1 : AppCompatActivity() {
    var networkChangeListener: MyReceiver = MyReceiver()
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "contean"

    lateinit var  button: Button
    lateinit var editText: EditText
    lateinit var mAdView2 : AdView
    lateinit var mAdView3 : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contean_freecalculator1)
        val adView = AdView(this)
        MobileAds.initialize(this) {}

        mAdView2 = findViewById(R.id.adView2)
        mAdView3 = findViewById(R.id.adView3)
        val adRequest = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest)
        mAdView3.loadAd(adRequest)

        adView.adSize = AdSize.LARGE_BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"


        button = findViewById(R.id.button2)
        editText = findViewById(R.id.editTextTextPersonName15)

        button.setOnClickListener {
            if (mInterstitialAd != null) {
                save_id(button.id)
                mInterstitialAd?.show(this)
            } else {
                val name = this.editText.editableText.toString()


                val intent = Intent(
                        this,
                        gatstartthentextv::class.java
                )

                intent.putExtra("name", name)
                startActivity(intent)
//            intent = Intent(applicationContext, gatstartthentextv::class.java)
//            startActivity(intent)
            }
        }
    }
    private fun save_id(id: Int) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("save", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("mID", id)
        editor.apply()

    }

    private fun load_id(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences("save", MODE_PRIVATE)
        return  sharedPreferences.getInt("mID", 0)
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
                        val name = editText.editableText.toString()


//                        val intent = Intent(
//                                this,
//                                gatstartthentextv::class.java
//                        )

                        intent = Intent(applicationContext,gatstartthentextv::class.java)

                        intent.putExtra("name", name)
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