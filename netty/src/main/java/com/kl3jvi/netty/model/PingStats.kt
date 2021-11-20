package com.kl3jvi.netty.model

import java.net.InetAddress

data class PingStats(
    val ia: InetAddress,
    val noPings: Long,
    val packetsLost: Long,
    val averageTimeTaken: Float,
    val maxTimeTaken: Float,
    val isReachable: Boolean
)