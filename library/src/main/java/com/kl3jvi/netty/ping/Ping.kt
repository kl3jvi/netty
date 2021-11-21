package com.kl3jvi.netty.ping

import com.kl3jvi.netty.model.ping_entities.PingOptions
import com.kl3jvi.netty.utils.CustomException
import java.net.InetAddress

class Ping private constructor(
    val addressString: String?,
    val address: InetAddress?,
    val delayBetweenScansMillis: Int,
    val times: Int,
    val pingOptions: PingOptions,
    val cancelled: Boolean
) {
    data class Builder(
        private var addressString: String? = null,
        private var address: InetAddress? = null,
        private var delayBetweenScansMillis: Int = 0,
        private var times: Int = 1,
        private var pingOptions: PingOptions = PingOptions(),
        private var cancelled: Boolean = false
    ) {
        fun onAddress(addressString: String?) = apply { this.addressString = addressString }

        fun onAddress(address: InetAddress?) = apply { this.address = address }

        fun setTimeOutMillis(timeoutMillis: Int) = apply {
            if (timeoutMillis > 0) this.pingOptions.timeoutMillis = timeoutMillis
            else throw CustomException("Time can't be negative!")
        }

        fun setTimeToLive(timeToLive: Int) =
            apply {
                if (timeToLive > 1) this.pingOptions.timeToLive = timeToLive
                else throw CustomException("Time to live should be greater than 1!")
            }

        fun setDelayMillis(delayBetweenScansMillis: Int) = apply {
            this.delayBetweenScansMillis = delayBetweenScansMillis
        }

        fun setTimes(times: Int) = apply {
            if (times < 0) throw CustomException("Times cannot be less than 0") else this.times =
                times
        }

        fun cancel() = apply { cancelled = true }

        fun doPing() = apply {
            Ping(
                addressString,
                address,
                delayBetweenScansMillis,
                times,
                pingOptions,
                cancelled
            )
        }


    }
}