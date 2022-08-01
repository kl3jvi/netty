package com.kl3jvi.netty.ping

data class Result(
    var ip: String,
    var isReachable: Boolean? = false,
    var error: String? = null,
    var timeTaken: Float? = 0F,
    var result: String? = "",
)

//fun String.mapToPingResult(): PingResult<Result> {
//    val ip = split(" ")[3]
//    if (true) {
//        return PingResult.Success(Result(""))
//    } else
//    return PingResult(
//        ip = ip
//    )
//}

sealed class PingResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : PingResult<T>(data)
    class Error<T>(message: String?) : PingResult<T>(null, message)
}

//sealed interface PingResult {
//    object Success : PingResult
//    object Error : PingResult()
//}