package com.example.kotlinapplist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.ui.MainActivity

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action?.endsWith(Consts.ACTION_NOTIF_CLICKED) == true){
            processNotificationClickAction(context)
        }
    }

    fun processNotificationClickAction(context: Context){
        context.startActivity(Intent(context, MainActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}