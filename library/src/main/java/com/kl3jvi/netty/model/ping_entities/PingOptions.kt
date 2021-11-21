package com.kl3jvi.netty.model.ping_entities

data class PingOptions constructor(
    var timeoutMillis: Int = 128,
    var timeToLive: Int = 1000
)
