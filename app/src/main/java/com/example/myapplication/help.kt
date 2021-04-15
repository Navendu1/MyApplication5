package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class help : AppCompatActivity() {

    lateinit var  textView: TextView
    lateinit var  button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val loadingdialog  = LoadingDialog(this)



        textView = findViewById(R.id.codeId)
        button = findViewById(R.id.button6)
        var count : Int = 1

        val redeem1 = getString(R.string.redeem1)
        val redeem2 = getString(R.string.redeem2)
        val redeem3 = getString(R.string.redeem3)
        textView.text =  redeem1




        button.setOnClickListener {
            loadingdialog.startLoading()
            count +=1

            Handler(Looper.getMainLooper()).postDelayed({
                loadingdialog.hideDialog()
                textView.isVisible= true

            }, (2000..6000).random().toLong())
            textView.isVisible=false

            when (count) {
                1 -> textView.text = redeem1
                2 -> {
                    textView.text =  redeem2
                }
                3 -> textView.text = redeem3
                else -> { // Note the block
                    textView.text = " nomore data"
                    button.isVisible=false
                }
            }
        }

    }
}