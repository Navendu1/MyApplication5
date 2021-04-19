package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var  button: Button
    lateinit var editText: EditText
    lateinit var editText1: EditText
    lateinit var editText2: EditText

    lateinit var  sharedPreferences: SharedPreferences

    var key_name : String ="email"
    var sharepref : String ="mypref"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


   button = findViewById(R.id.button0)
        editText = findViewById(R.id.editTextTextPersonName2)
        editText1 = findViewById(R.id.editTextTextPersonName3)
        editText2 = findViewById(R.id.editTextTextEmailAddress2)




            sharedPreferences = getSharedPreferences(sharepref, MODE_PRIVATE)

            var name: String? =sharedPreferences.getString(key_name,null)

            if ( name !=null){

                intent = Intent(applicationContext, MainActivity2::class.java)
                startActivity(intent)
            }

            button.setOnClickListener {

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(key_name,editText2.toString())
                editor.apply()


                logIn()
            }







        }





    private fun logIn(){
            if(editText.text.toString().isEmpty()){
                editText.error = "Free Fire ID"
                editText.requestFocus()
                return
            }
            if(editText1.text.toString().isEmpty()){
                editText1.error = "Please enter Name"
                editText1.requestFocus()
                return
            }
            if(editText2.text.toString().isEmpty()){
                editText2.error = "Please enter email"
                editText2.requestFocus()
                return
            }
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
            Toast.makeText(this, "hello fake app", Toast.LENGTH_SHORT).show()

    }
}