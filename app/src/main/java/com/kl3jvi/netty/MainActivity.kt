package com.kl3jvi.netty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kl3jvi.netty.ping.Ping

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Ping.Builder()
            .setTimeOutMillis(100)
            .onAddress("192.168.1.1")
            .doPing()


    }
}