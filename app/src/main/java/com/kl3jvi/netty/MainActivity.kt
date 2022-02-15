package com.kl3jvi.netty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kl3jvi.netty.model.ping_entities.PingOptions
import com.kl3jvi.netty.network_utils.NetworkUtils.isConnectedToInternet
import com.kl3jvi.netty.ping.Ping.doPing
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            val pingOptions = PingOptions.Builder()
                .setIpToPing("192.168.1.1")
                .setTimeToLive(1000)
                .setRepetitionsNumber(1)
                .build()

            doPing(pingOptions = pingOptions) {
                Log.e("RESULT", it?.data.toString())
            }


        }
    }


    override fun onStart() {
        super.onStart()
        isConnectedToInternet(this) { isConnected ->
            Log.e("connection", isConnected.toString())
        }
    }
}