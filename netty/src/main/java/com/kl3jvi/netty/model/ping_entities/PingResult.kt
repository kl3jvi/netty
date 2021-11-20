package com.kl3jvi.netty.model.ping_entities

import java.net.InetAddress

class PingResult(val address: InetAddress? = null) {
    var isReachable = false
    var error: String? = null
    var timeTaken = 0f
    var fullString: String? = null
    var result: String? = null
    fun hasError(): Boolean {
        return error != null
    }
}
