package com.example.applicationkt.connected

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

        val lights: Button = findViewById(R.id.lights)
        val lightsOff: Button = findViewById(R.id.lightsOff)

        lights.setOnClickListener{
            lightsON("https://pentastyle-gull-3744.dataplicity.io/on")
        }

        lightsOff.setOnClickListener{
            lightsOFF("https://pentastyle-gull-3744.dataplicity.io/off")
        }

        //run("https://pentastyle-gull-3744.dataplicity.io")
        //lights("https://pentastyle-gull-3744.dataplicity.io/lights")
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response): Unit =
                response.body!!.string().let { Log.v("rasp", it) }
        })
    }

    fun lightsON(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response): Unit =
                response.body!!.string().let { Log.v("lights", it) }
        })
    }

    fun lightsOFF(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response): Unit =
                response.body!!.string().let { Log.v("lightsOff", it) }
        })
    }
}