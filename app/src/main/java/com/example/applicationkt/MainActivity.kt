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

        val button = findViewById<Button>(R.id.connect)
        button.setOnClickListener{
            val intent = Intent(this, ConnectedFragment::class.java)
            startActivity(intent)

        }

    }

}