package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {
//   lateinit  var dialog: AlertDialog


    override fun onReceive(context: Context, intent: Intent) {

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (!chkinternet.isConnectedToInternet(context)) {
            val ab :AlertDialog.Builder = AlertDialog.Builder(context)

  val view: View =  LayoutInflater.from(context).inflate(R.layout.alert_dialog,null)

            ab.setView(view)
            ab.setCancelable(false)

         val button = view.findViewById<Button>(R.id.btn_try404)

            val dialog = ab.create()
            dialog.show()
            ab.setCancelable(false)

   button.setOnClickListener {
       dialog.dismiss()
       onReceive(context, intent)
   }



        }


    }
}