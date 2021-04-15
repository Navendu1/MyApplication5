package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class gatstartthentextv : AppCompatActivity() {

    lateinit var  button: Button
    lateinit var textView: TextView
//    lateinit var  textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gatstartthentextv)

        button = findViewById(R.id.button4)
        textView = findViewById(R.id.textView11)



    val name =intent.getStringExtra("name")
    this.textView.text = "Result \n $name$ \n Please waiting for 72-120 hours, \n if not working please try again. Actually lot of user so  diamond sending process slow. "

        button.setOnClickListener {

            intent = Intent(applicationContext, lestActivity::class.java)
            startActivity(intent)


        }

    }
}