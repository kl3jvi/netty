package com.kl3jvi.netty.utils

import com.kl3jvi.netty.model.ping_entities.PingResult
import com.kl3jvi.netty.model.ping_entities.PingStats

interface PingListener {
    fun onResult(pingResult: PingResult)
    fun onFinished(pingStats: PingStats)
    fun onError(e: Exception)
}
