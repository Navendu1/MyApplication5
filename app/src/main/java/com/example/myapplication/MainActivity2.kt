package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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


}