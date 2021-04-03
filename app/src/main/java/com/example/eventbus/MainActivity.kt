package com.example.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eventbus.Events.MessageEvent
import com.example.eventbus.Events.MessageEvent2
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private lateinit var MyText:TextView
    private lateinit var RunCodeButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyText=findViewById(R.id.MyText)
        RunCodeButton=findViewById(R.id.RunCodeButton)
        EventBus.getDefault().register(this)
        RunCodeButton.setOnClickListener(View.OnClickListener {
            val intent:Intent= Intent(this,MyIntentService::class.java)
            intent.putExtra("Key1","This is Message One ")
            intent.putExtra("Key2","This is Message Two")
            startService(intent)
        })
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun MyEventReceived(Event:MessageEvent)
    {
        MyText.append("${Event.message} \n")
        MyText.append(Thread.currentThread().name+"\n")
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun MyEventReceived(Event:MessageEvent2)
    {
        Toast.makeText(this,"Second",Toast.LENGTH_LONG).show()
        MyText.append("Second: ${Event.message} \n")
        MyText.append(Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}