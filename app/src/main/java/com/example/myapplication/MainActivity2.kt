package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

         val opnionReward:Button = findViewById(R.id.opinionRewards)
         val redeemCode:Button = findViewById(R.id.redeemCode)
         val freeDaimond:Button = findViewById(R.id.calculator)
       opnionReward.setOnClickListener {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, opinionrewards::class.java)
            startActivity(intent) }
       redeemCode.setOnClickListener{
           intent = Intent(applicationContext, redeemcode::class.java)
            startActivity(intent)}

      freeDaimond.setOnClickListener {
          intent = Intent(applicationContext, contean_freecalculator1::class.java)
            startActivity(intent)}

    }

}