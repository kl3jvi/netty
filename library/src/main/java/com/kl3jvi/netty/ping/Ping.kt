package com.kl3jvi.netty.ping

import android.Manifest.permission.INTERNET
import androidx.annotation.RequiresPermission
import com.kl3jvi.netty.model.ping_entities.PingOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object Ping {
    /**
     * Ping on ip address specified.
     * Requires Internet permission.
     */
    @RequiresPermission(INTERNET)
    suspend fun doPing(
        pingOptions: PingOptions,
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
        callback: (pingResult: PingResult<String>?) -> Unit
    ) = coroutineScope {

        launch(coroutineDispatcher) {

            val ip = pingOptions.ip
            val timeout = pingOptions.timeToLive
            val times = pingOptions.times

            val pingCmd = "ip neigh show"
            try {
                val runtime = Runtime.getRuntime()
                val process = runtime.exec(pingCmd)
                val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
                var inputLine: String?
                while (bufferedReader.readLine().also { inputLine = it } != null) {
                    val result = PingResult.Success(inputLine)
                    callback(result)
                }
                bufferedReader.close()
                process.destroy()
            } catch (e: IOException) {
                e.printStackTrace()
                println(e)
            }
        }
    }
}




