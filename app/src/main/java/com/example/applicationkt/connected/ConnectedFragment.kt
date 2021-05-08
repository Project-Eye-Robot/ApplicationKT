package com.example.applicationkt.connected

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.VideoView
import androidx.lifecycle.ViewModelProvider
import com.example.applicationkt.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ConnectedFragment : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connected)
        var isLightsON = false

        val lights: ImageButton = findViewById(R.id.lights)
        val forward: ImageButton = findViewById(R.id.arrow_up)
        val backward: ImageButton = findViewById(R.id.arrow_down)
        val left: ImageButton = findViewById(R.id.arrow_left)
        val right: ImageButton = findViewById(R.id.arrow_right)
        val baseUrl = "https://pentastyle-gull-3744.dataplicity.io"
        val stream: VideoView = findViewById(R.id.streaming)

        lights.setOnClickListener{
            if(isLightsON){
                CallUrl("${baseUrl}/off")
                isLightsON = false
            }
            else{
                CallUrl("${baseUrl}/on")
                isLightsON = true
            }
        }

        forward.setOnClickListener{
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
        }

    }

    fun CallUrl(url: String){
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response): Unit =
                    response.body!!.string().let { Log.v("request", it) }
        })
    }
}