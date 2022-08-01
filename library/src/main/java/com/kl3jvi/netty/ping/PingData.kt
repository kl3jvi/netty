package com.kl3jvi.netty.ping

data class PingData(
    val ip: String? = null,
    val bytesAmount: String? = null,
    var isReachable: Boolean = false,
    var timeTaken: String? = null
)

