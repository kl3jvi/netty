package com.kl3jvi.netty.utils

import com.kl3jvi.netty.model.PingResult
import com.kl3jvi.netty.model.PingStats

interface PingListener {
    fun onResult(pingResult: PingResult)
    fun onFinished(pingStats: PingStats?)
    fun onError(e: Exception?)
}
