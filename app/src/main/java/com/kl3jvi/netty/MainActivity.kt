package com.kl3jvi.netty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kl3jvi.netty.databinding.ActivityMainBinding
import com.kl3jvi.netty.network_utils.NetworkUtils.isConnectedToInternet
import com.kl3jvi.netty.ping.Ping.doPing
import com.kl3jvi.netty.ping.Ping.stop
import com.kl3jvi.netty.ping.PingResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val flowoo = doPing("192.168.1.1").onEach {
            when (it) {
                is PingResult.Error -> Log.e("Error", it.message.toString())
                is PingResult.Success -> Log.e("Success", it.data.toString())
            }
        }
        val t = lifecycleScope.launch {

            flowoo.collect()

        }

        binding.button.setOnClickListener { stop(t)
            val t1 = lifecycleScope.launch {

                flowoo.collect()

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


