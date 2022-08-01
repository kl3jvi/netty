package com.kl3jvi.netty.ping

import android.Manifest.permission.INTERNET
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress


object Ping {

    private lateinit var process: Process
    private lateinit var runtime: Runtime
    private lateinit var bufferedReader: BufferedReader

    private const val PING_COMMAND = "ping"

    /**
     * It pings the given IP address and returns the result as a stream of flow.
     *
     * @param stringIp String
     */
    @RequiresPermission(INTERNET)
    fun doPing(stringIp: String) = flow {
        val ip = InetAddress.getByName(stringIp)
        val pingCmd = "$PING_COMMAND ${ip.hostAddress}"

        runtime = Runtime.getRuntime()
        process = runtime.exec(pingCmd)
        bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        var inputLine: String?

        while (bufferedReader.readLine().also { inputLine = it } != null) {
            val result = inputLine?.getPingStats()
            if (result != null) emit(PingResult.Success(result))
            else emit(PingResult.Error("Error Occurred"))
        }
        bufferedReader.close()
        process.destroy()

    }.catch { emit(PingResult.Error(it.message)) }.cancellable().flowOn(Dispatchers.IO)



    /**
     * "Stop the job by cancelling all of its children."
     *
     * The function takes a Job as a parameter and returns a Job. The job is cancelled by calling the cancelChildren
     * function on the job. The cancelChildren function takes a cause as a parameter. The cause is a CancellationException
     * with the message "Stopped Ping!"
     *
     * @param job Job - The job that is being cancelled.
     */
    fun stop(job: Job) = job.cancelChildren(cause = CancellationException("Stopped Ping!"))


    /**
     * If the string contains "Request timeout" or "cannot resolve",
     * return an empty PingData object. Otherwise, return a
     * PingData object with the bytes amount, isReachable, and timeTaken
     *
     * @return A PingData object
     */
    private fun String.getPingStats(): PingData? {
        val index = indexOf(":")
        if (index == -1) return null

        val ip = split("from ", ":")[1]

        val bytesAmount = substring(0..1)
        val isReachable = true
        val timeTaken = split("time=").lastOrNull() ?: "0 ms"
        return PingData(ip, bytesAmount, isReachable, timeTaken)
    }


}



