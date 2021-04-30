package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class help : AppCompatActivity() {

    lateinit var mAdView9 : AdView

    var networkChangeListener: MyReceiver = MyReceiver()

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "help"
    lateinit var help: TextView
    lateinit var textView : TextView
    lateinit var refresh: TextView
    lateinit var noData: ImageView

    lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        scrollView = findViewById(R.id.scrollView2)
        noData = findViewById(R.id.noData)
        noData.isVisible=false
        val loadingdialog  = LoadingDialog(this)
        //ad baner
        val adView = AdView(this)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"


        MobileAds.initialize(this) {}

        mAdView9 = findViewById(R.id.adView9)
        val adRequest = AdRequest.Builder().build()
        mAdView9.loadAd(adRequest)



        textView = findViewById(R.id.codeId)
//        button = findViewById(R.id.button6)
//        val imageView: ImageView = findViewById(R.id.imageView9)

        help = findViewById(R.id.help)
        refresh = findViewById(R.id.refresh)
        var count : Int = 1

        val redeem1 = getString(R.string.redeem1)
        val redeem2 = getString(R.string.redeem2)
        val redeem3 = getString(R.string.redeem3)
        val redeem4 = getString(R.string.redeem4)
        val redeem5 = getString(R.string.redeem5)
        val redeem6 = getString(R.string.redeem6)
        val redeem7 = getString(R.string.redeem7)
        textView.text =  redeem1


        refresh.setOnClickListener {
            if (mInterstitialAd != null) {
                save_id(refresh.id)
                mInterstitialAd?.show(this)
            } else {
                loadingdialog.startLoading()
                count += 1

                Handler(Looper.getMainLooper()).postDelayed({
                    loadingdialog.hideDialog()
                    textView.isVisible = true

                }, (2000..3000).random().toLong())
                textView.isVisible = false

                when (count) {
                    1 -> textView.text = redeem1
                    2 ->
                        textView.text = redeem2

                    3 -> textView.text = redeem3
                    4 -> textView.text = redeem4
                    5 -> textView.text = redeem5
                    6 -> textView.text = redeem6
                    7 -> textView.text = redeem7
                    else -> { // Note the block
                        noData.isVisible = true
                        scrollView.isVisible = false
                        refresh.isVisible = false
                    }
                }
            }
        }

        help.setOnClickListener {
            if (mInterstitialAd != null) {
                save_id(help.id)
                mInterstitialAd?.show(this)
            } else {
                intent = Intent(applicationContext, redeemcode::class.java)
                startActivity(intent)

                // goto help activity
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
                Log.d(TAG, adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {

                    override fun onAdDismissedFullScreenContent() {
                        intent = when ( load_id()) {
                            R.id.help -> {
                                Intent(applicationContext, redeemcode ::class.java)
                            }
//                            R.id.refresh -> {
//                                Intent(applicationContext, com.example.myapplication.help::class.java)
//                            }


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