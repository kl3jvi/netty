package com.kl3jvi.netty.model.ping_entities

class PingOptions private constructor(
    var timeoutMillis: Int = 128,
    val timeToLive: Int = 1000
) {
    data class Builder(
        var timeoutMillis: Int,
        var timeToLive: Int
    ) {
        fun setTimeOutMillis(timeoutMillis: Int) = apply { this.timeoutMillis = timeoutMillis }
        fun setDelayMillis(timeToLive: Int) = apply { this.timeToLive = timeToLive }
        fun build() = PingOptions(timeoutMillis, timeToLive)
    }
}
