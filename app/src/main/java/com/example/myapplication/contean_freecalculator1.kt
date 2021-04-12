package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class contean_freecalculator1 : AppCompatActivity() {

    lateinit var  button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contean_freecalculator1)


        button = findViewById(R.id.button2)

        button.setOnClickListener {
            intent = Intent(applicationContext, gatstartthentextv::class.java)
            startActivity(intent)
        }
    }
}