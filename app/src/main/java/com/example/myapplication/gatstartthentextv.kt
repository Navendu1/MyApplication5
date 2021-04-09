package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class gatstartthentextv : AppCompatActivity() {

    lateinit var  button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gatstartthentextv)

        button = findViewById(R.id.button4)

        button.setOnClickListener {
            intent = Intent(applicationContext, lestActivity::class.java)
            startActivity(intent)
        }
    }
}