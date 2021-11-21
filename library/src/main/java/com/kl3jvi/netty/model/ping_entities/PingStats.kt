package com.kl3jvi.netty.model.ping_entities

import com.kl3jvi.netty.ping.Ping
import java.net.InetAddress

data class PingStats(
    val ia: InetAddress,
    val noPings: Long,
    val packetsLost: Long,
    val averageTimeTaken: Float,
    val maxTimeTaken: Float,
    val isReachable: Boolean
)