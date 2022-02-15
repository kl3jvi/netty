package com.kl3jvi.netty.model.ping_entities

class PingOptions private constructor(
    var ip: String?,
    var times: Int?,
    var timeToLive: Int = 1000
) {
    data class Builder(
        var ip: String? = null,
        var times: Int? = null,
        var timeToLive: Int = 1000
    ) {
        fun setIpToPing(ip: String?) = apply { this.ip = ip }
        fun setRepetitionsNumber(times: Int?) = apply { this.times = times }
        fun setTimeToLive(timeToLive: Int) = apply { this.timeToLive = timeToLive }
        fun build() = PingOptions(ip, times, timeToLive)
    }
}
