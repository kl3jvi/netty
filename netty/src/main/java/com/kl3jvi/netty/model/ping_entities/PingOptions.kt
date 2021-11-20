package com.kl3jvi.netty.model.ping_entities

import com.kl3jvi.netty.utils.CustomException

class PingOptions private constructor(
    var timeoutMillis: Int,
    val timeToLive: Int
) {
    data class Builder(
        var timeoutMillis: Int = 128,
        var timeToLive: Int = 1000
    ) {
        fun setTimeOutMillis(timeoutMillis: Int) = apply {
            if (timeoutMillis > 0) this.timeoutMillis = timeoutMillis
            else throw CustomException("Time can't be negative!")
        }

        fun setTimeToLive(timeToLive: Int) =
            apply {
                if (timeToLive > 1) this.timeToLive = timeToLive
                else throw CustomException("Time to live should be greater than 1!")
            }

        fun build() = PingOptions(timeoutMillis, timeToLive)
    }
}
