package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class contean_freecalculator1 : AppCompatActivity() {

    lateinit var  button: Button
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contean_freecalculator1)


        button = findViewById(R.id.button2)
        editText = findViewById(R.id.editTextTextPersonName15)

        button.setOnClickListener {
            val name = this.editText.editableText.toString()


            val intent =Intent(
                    this,
                    gatstartthentextv::class.java
            )

            intent.putExtra("name",name)
            startActivity(intent)
//            intent = Intent(applicationContext, gatstartthentextv::class.java)
//            startActivity(intent)
        }
    }

}