package com.example.xox_game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)

        }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            val intent =Intent(this,GameActivity::class.java)
            startActivity(intent)

        }
    }
}