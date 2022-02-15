package com.kl3jvi.netty.utils

object IPUtils {
    private val IPV4_REGEX =
        Regex("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$")

    private val IPV6_STD_PATTERN = Regex("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$")

    private val IPV6_HEX_COMPRESSED_PATTERN =
        Regex("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$")

    fun String.isIPv4Address(): Boolean {
        return IPV4_REGEX.matches(this)
    }

    private fun String.isIPV6StdAddress(): Boolean {
        return IPV6_STD_PATTERN.matches(this)
    }

    private fun String.isIPV6HexCompressedAddress(): Boolean {
        return IPV6_HEX_COMPRESSED_PATTERN.matches(this)
    }

    fun String.isIPV6Address(): Boolean {
        return isIPV6HexCompressedAddress() || isIPV6StdAddress()
    }


}