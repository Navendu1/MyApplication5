package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class help : AppCompatActivity() {

    lateinit var  textView: TextView
    lateinit var  button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)



        textView = findViewById(R.id.codeId)
        button = findViewById(R.id.button6)
        var count : Int = 1

        val redeem1 = getString(R.string.redeem1)
        val redeem2 = getString(R.string.redeem2)
        val redeem3 = getString(R.string.redeem3)
        textView.text =  redeem1

        button.setOnClickListener {
            count +=1
            when (count) {
                1 -> textView.text = redeem1
                2 -> {
                    textView.text =  redeem2
                }
                3 -> textView.text = redeem3
                else -> { // Note the block
                    Toast.makeText(this, "no more data avilabe", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
}