package com.kl3jvi.netty.model

import java.net.InetAddress

class PingResult(val address: InetAddress) {
    var isReachable = false
    var error: String? = null
    var timeTaken = 0f
    var fullString: String? = null
    var result: String? = null
    fun hasError(): Boolean {
        return error != null
    }
}
