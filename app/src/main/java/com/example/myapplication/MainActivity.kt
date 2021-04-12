package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var  button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   button = findViewById(R.id.button)



//         val loadingdialog  = LoadingDialog(this)
//
//        loadingdialog.startLoading()
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            loadingdialog.hideDialog()
//        }, (2000..8000).random().toLong())

        button.setOnClickListener {
            Toast.makeText(this, "hello fake app", Toast.LENGTH_SHORT).show()




            intent = Intent(applicationContext, help::class.java)
            startActivity(intent)

        }

    }


}