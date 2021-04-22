package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.core.view.isVisible
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity()   {
    lateinit var opnionReward:Button






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        checkConnection()



          opnionReward = findViewById(R.id.opinionRewards)
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

    private fun checkConnection() {
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo) {
            if (
                networkInfo.type == ConnectivityManager.TYPE_WIFI
            )
            else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE)
            else {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.alert_dialog)
                dialog.setCanceledOnTouchOutside(false)
                dialog.window!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )

                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val dialog1: Button = findViewById(R.id.btn_try404)
                dialog1.setOnClickListener {
                    recreate()
                }
                dialog.show()
            }
        }
    }


}