package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog


class LoadingDialog( var activity: Activity) {
    lateinit var dialog: AlertDialog

    //
//     LoadingDialog (context: Context){
//         activity = context as Activity
//    }

//var dialog: AlertDialog? = null
//var activity: Activity? = null
//
//fun LoadingDialog(myActivity: Activity?) {
//    activity = myActivity
//}


fun startLoading()  {


        val builder = AlertDialog.Builder(activity)


        val inflater =  activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.customloading, null))
        builder.setCancelable(false);

        dialog= builder.create()
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss();
    }

}