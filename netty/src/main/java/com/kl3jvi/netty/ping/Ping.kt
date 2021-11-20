package com.kl3jvi.netty.ping

import com.kl3jvi.netty.utils.PingListener
import java.net.InetAddress

class Ping private constructor(
    val addressString: String? = null,
    val address: InetAddress? = null,
    val delayBetweenScansMillis: Int = 0,
    val times: Int = 1,
    val cancelled: Boolean = false
) {




}