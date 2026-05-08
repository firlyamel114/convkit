package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        val iconLeftButton = findViewById<ImageButton>(R.id.icon_left)
        iconLeftButton.setOnClickListener {
            // Navigasi ke layout lainnya di sini
            val intent = Intent(this , DropdownActivity::class.java)
            startActivity(intent)
        }
        }
    }