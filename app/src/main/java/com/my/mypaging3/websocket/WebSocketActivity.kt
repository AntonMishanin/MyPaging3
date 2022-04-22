package com.my.mypaging3.websocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.my.mypaging3.R
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

class WebSocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_socket)

        val client = OkHttpClient.Builder()
            .pingInterval(40, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url("wss://ws.finnhub.io?token=c9h2ipaad3iblo2fqvg0")
            .build()

        val ws: WebSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                //  super.onClosed(webSocket, code, reason)
                Log.e("EE", "onClosed")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                //  super.onClosing(webSocket, code, reason)
                Log.e("EE", "onClosing")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                //  super.onFailure(webSocket, t, response)
                Log.e("EE", "onFailure $t $response")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // super.onMessage(webSocket, text)
                Log.e("EE", "onMessage $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // super.onMessage(webSocket, bytes)
                Log.e("EE", "onMessage bytes = $bytes")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                //  super.onOpen(webSocket, response)
                Log.e("EE", "onOpen response = $response")
                webSocket.send("{\"type\":\"subscribe\",\"symbol\":\"BINANCE:BTCBRL\"}")//Critical: syntax important - use ". Don't use '
            }
        })
        //ws.close()
        //in onOpen
//   webSocket.send("{'type':'subscribe','symbol':'AAPL'}")
        //   webSocket.send("{'type':'subscribe','symbol':'AMZN'}")
        //   webSocket.send("{'type':'subscribe','symbol':'BINANCE:BTCUSDT'}")
        //   webSocket.send("{'type':'subscribe','symbol':'IC MARKETS:1'}")

        client.dispatcher.executorService.shutdown()
    }
}