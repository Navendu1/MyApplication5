package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    lateinit var  button: Button
    lateinit var editText: EditText
    lateinit var editText1: EditText
    lateinit var editText2: EditText

    lateinit var  sharedPreferences: SharedPreferences

    var key_name : String ="email"
    var sharepref : String ="mypref"

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ad adMob
        MobileAds.initialize(this) {}
        //adMob
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        })


        if(CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {

            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
        MobileAds.initialize(this) {}


   button = findViewById(R.id.button0)
        editText = findViewById(R.id.editTextTextPersonName2)
        editText1 = findViewById(R.id.editTextTextPersonName3)
        editText2 = findViewById(R.id.editTextTextEmailAddress2)




            sharedPreferences = getSharedPreferences(sharepref, MODE_PRIVATE)

            var name: String? =sharedPreferences.getString(key_name,null)

            if ( name !=null){

                intent = Intent(applicationContext, MainActivity2::class.java)
                startActivity(intent)
            }

            button.setOnClickListener {

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(key_name,editText2.toString())
                editor.apply()


                logIn()
            }







        }





    private fun logIn(){
            if(editText.text.toString().isEmpty()){
                editText.error = "Free Fire ID"
                editText.requestFocus()
                return
            }
            if(editText1.text.toString().isEmpty()){
                editText1.error = "Please enter Name"
                editText1.requestFocus()
                return
            }
            if(editText2.text.toString().isEmpty()){
                editText2.error = "Please enter email"
                editText2.requestFocus()
                return
            }
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)


    }
    private fun checkConnection() {
        val manager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo) {
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "wifi canm", Toast.LENGTH_SHORT).show()
            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "mobile data", Toast.LENGTH_SHORT).show()
            }
        }

        else {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog)

            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            )

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            
            dialog.show()
        }

    }


}