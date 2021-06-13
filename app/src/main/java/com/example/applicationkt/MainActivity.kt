package com.example.applicationkt

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.applicationkt.connected.ConnectedFragment
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseUrl = "http://192.168.1.61:80"
        val button = findViewById<Button>(R.id.connect)


        val request = Request.Builder()
                .url(baseUrl)
                .build()

        val intent = Intent(this, ConnectedFragment::class.java)
        val loader : LottieAnimationView = findViewById(R.id.animationLoader)


        button.setOnClickListener{
            loader.visibility = View.VISIBLE

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Handler(Looper.getMainLooper()).post {
                        loader.visibility = View.INVISIBLE
                        Toast.makeText(this@MainActivity, "Failed To Connect", Toast.LENGTH_SHORT).show()
                    }

                }
                override fun onResponse(call: Call, response: Response) {
                    startActivity(intent)}
            } )

        }

    }
}
