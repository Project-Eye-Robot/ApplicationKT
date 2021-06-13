package com.example.applicationkt.connected

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.applicationkt.R
import okhttp3.*
import java.io.IOException


class    ConnectedFragment : AppCompatActivity(){
    private val client = OkHttpClient()

    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connected)
        var isLightsON = false
        var isHonkOn = false

        val lights: ImageButton = findViewById(R.id.lights)
        val honk : ImageButton = findViewById(R.id.honk)

        val forward: CardView = findViewById(R.id.arrow_up)
        val backward: CardView = findViewById(R.id.arrow_down)
        val left: CardView = findViewById(R.id.arrow_left)
        val right: CardView = findViewById(R.id.arrow_right)
        val baseUrl = "http://192.168.1.61:80"
        val baseUrl2 = "http://192.168.43.75:80"
        val webView = findViewById<View>(R.id.streaming) as WebView

        true.also { webView.settings.javaScriptEnabled = it }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(baseUrl)
                return true
                }
            }
        webView.loadUrl("${baseUrl}/camera")

        lights.setOnClickListener{
            if(isLightsON){
                CallUrl("${baseUrl}/lightOff")
                isLightsON = false
            }
            else{
                CallUrl("${baseUrl}/lightOn")
                isLightsON = true
            }
        }

        honk.setOnClickListener{
            if(isHonkOn){
                CallUrl("${baseUrl}/honkOff")
                isHonkOn = false
            }
            else{
                CallUrl("${baseUrl}/honkOn")
                isHonkOn = true
            }
        }

        forward.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.v("forward", "forward")
                    CallUrl("${baseUrl}/F")
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.v("stop forward", "stop forward")
                    CallUrl("${baseUrl}/Stop")
                    return@OnTouchListener true
                }
            }
            false
        })

        backward.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    CallUrl("${baseUrl}/B")
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.v("stop forward", "stop forward")
                    CallUrl("${baseUrl}/Stop")
                    return@OnTouchListener true
                }
            }
            false
        })

        left.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.v("forward", "forward")
                    CallUrl("${baseUrl}/L")
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.v("stop forward", "stop forward")
                    CallUrl("${baseUrl}/Stop")
                    return@OnTouchListener true
                }
            }
            false
        })

        right.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.v("forward", "forward")
                    CallUrl("${baseUrl}/R")
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.v("stop forward", "stop forward")
                    CallUrl("${baseUrl}/Stop")
                    return@OnTouchListener true
                }
            }
            false
        })

        /*forward.setOnClickListener{
            CallUrl("${baseUrl}/F")
        }
        backward.setOnClickListener{
            CallUrl("${baseUrl}/B")
        }
        left.setOnClickListener{
            CallUrl("${baseUrl}/L")
        }
        right.setOnClickListener{
            CallUrl("${baseUrl}/R")
        }*/

    }

    fun CallUrl(url: String){
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { Log.v("request", e.toString()) }
            override fun onResponse(call: Call, response: Response): Unit =
                    response.body!!.string().let { Log.v("request", it) }
        })
    }
}