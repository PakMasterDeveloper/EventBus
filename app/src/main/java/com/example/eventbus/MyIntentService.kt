package com.example.eventbus

import android.app.IntentService
import android.content.Intent
import android.content.Context
import com.example.eventbus.Events.MessageEvent
import com.example.eventbus.Events.MessageEvent2
import org.greenrobot.eventbus.EventBus


class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        if(intent!=null)
        {
            val Message_One:String?=intent.getStringExtra("Key1")
            val Message_Two:String?=intent.getStringExtra("Key2")
            //do some work here
            var messge:MessageEvent= MessageEvent("$Message_One from Services")
            EventBus.getDefault().post(messge)
            Thread.sleep(3000)
            var messge2= MessageEvent2("$Message_Two from Service")
            EventBus.getDefault().post(messge2)
        }

    }

}