package com.example.applicationkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.applicationkt.connected.ConnectedFragment
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isConnectOn = false

        val baseUrl = "http://192.168.1.61:80"
        val button = findViewById<Button>(R.id.connect)

        /*button.setOnClickListener{
            val intent = Intent(this, ConnectedFragment::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            Log.v("url",CallUrl("${baseUrl}/").toString())

        }*/

        button.setOnClickListener{
            Log.v("url",CallUrl("${baseUrl}/").toString())

            val request = Request.Builder()
                .url(baseUrl)
                .build()

            val intent = Intent(this, ConnectedFragment::class.java)

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    startActivity(intent)}
            } )

        }

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
