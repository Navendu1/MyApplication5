package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.core.view.isVisible
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity()   {


    var networkChangeListener: MyReceiver = MyReceiver()

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivity2"

    lateinit var mAdView : AdView
    lateinit var mAdView1 : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        mAdView1 = findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView1.loadAd(adRequest)



        val opnionReward:Button= findViewById(R.id.opinionRewards)
        val redeemCode:Button = findViewById(R.id.redeemCode)
        val freeDaimond:Button = findViewById(R.id.calculator)

        val adView = AdView(this)

        adView.adSize = AdSize.LARGE_BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        

//ad loding



                opnionReward.setOnClickListener {

                    if (mInterstitialAd != null) {
                        save_id(opnionReward.id)
                        mInterstitialAd?.show(this)
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.")
                        intent = Intent(applicationContext, opinionrewards::class.java)
                        startActivity(intent)
//                }redeemCode.isVisible = false
                    }
            }


        redeemCode.setOnClickListener {
            if (mInterstitialAd != null) {
                save_id(redeemCode.id)
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")

                intent = Intent(applicationContext, help::class.java)
                startActivity(intent)
            }
        }


            freeDaimond.setOnClickListener {
                if (mInterstitialAd != null) {
                    save_id(freeDaimond.id)
                    mInterstitialAd?.show(this)
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    intent = Intent(applicationContext, contean_freecalculator1::class.java)
                    startActivity(intent)
                }
        }



    }

    private fun save_id(id: Int) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("save", MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
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
                Log.d(TAG, adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {

                    override fun onAdDismissedFullScreenContent() {
                        intent = when ( load_id()) {
                            R.id.opinionRewards -> {
                                Intent(applicationContext, opinionrewards::class.java)
                            }
                            R.id.redeemCode -> {
                                Intent(applicationContext, help::class.java)
                            }
                            R.id.calculator -> {
                                Intent(applicationContext, contean_freecalculator1::class.java)
                            }

                            else -> return
                        }

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