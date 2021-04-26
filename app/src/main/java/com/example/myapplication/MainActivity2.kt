package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity()   {


    var networkChangeListener: MyReceiver = MyReceiver()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MobileAds.initialize(this)




        val opnionReward:Button= findViewById(R.id.opinionRewards)
         val redeemCode:Button = findViewById(R.id.redeemCode)
         val freeDaimond:Button = findViewById(R.id.calculator)



        




                opnionReward.setOnClickListener {
                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, opinionrewards::class.java)
                startActivity(intent)
//                redeemCode.isVisible = false
            }


        redeemCode.setOnClickListener {
            intent = Intent(applicationContext, help::class.java)
            startActivity(intent)
        }


            freeDaimond.setOnClickListener {
                intent = Intent(applicationContext, contean_freecalculator1::class.java)
                startActivity(intent)

        }



    }
    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener, filter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }




}