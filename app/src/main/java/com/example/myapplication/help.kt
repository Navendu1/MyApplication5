package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class help : AppCompatActivity() {

    lateinit var help: TextView
    lateinit var textView : TextView
    lateinit var refresh: ImageView
    lateinit var noData: ImageView

    lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        scrollView = findViewById(R.id.scrollView2)
        noData = findViewById(R.id.noData)
        noData.isVisible=false
        val loadingdialog  = LoadingDialog(this)



        textView = findViewById(R.id.codeId)
//        button = findViewById(R.id.button6)
//        val imageView: ImageView = findViewById(R.id.imageView9)

        help = findViewById(R.id.help)
        refresh = findViewById(R.id.refresh)
        var count : Int = 1

        val redeem1 = getString(R.string.redeem1)
        val redeem2 = getString(R.string.redeem2)
        val redeem3 = getString(R.string.redeem3)
        val redeem4 = getString(R.string.redeem4)
        val redeem5 = getString(R.string.redeem5)
        val redeem6 = getString(R.string.redeem6)
        val redeem7 = getString(R.string.redeem7)
        textView.text =  redeem1


        refresh.setOnClickListener {
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
            loadingdialog.startLoading()
            count +=1

            Handler(Looper.getMainLooper()).postDelayed({
                loadingdialog.hideDialog()
                textView.isVisible= true

            }, (2000..3000).random().toLong())
            textView.isVisible=false

            when (count) {
                1 -> textView.text = redeem1
                2 ->
                    textView.text =  redeem2

                3 -> textView.text = redeem3
                4 -> textView.text = redeem4
                5 -> textView.text = redeem5
                6 -> textView.text = redeem6
                7 -> textView.text = redeem7
                else -> { // Note the block
                    noData.isVisible= true
                    scrollView.isVisible = false
                    refresh.isVisible=false
                }
            }
        }

        help.setOnClickListener {
            Toast.makeText(this, "help", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, redeemcode::class.java)
            startActivity(intent)

            // goto help activity
        }






    }
}