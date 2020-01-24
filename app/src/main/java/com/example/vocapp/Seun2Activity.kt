package com.example.vocapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Seun2Activity : AppCompatActivity() {
    private lateinit var b: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seun_2)

        b = findViewById(R.id.easyMode)
        b.setOnClickListener {
            val intent = Intent(this, main_game::class.java)
            startActivity(intent)
        }
        b = findViewById(R.id.intermediateMode)
        b.setOnClickListener {
            val intent = Intent(this, main_game::class.java)
            startActivity(intent)
        }
        b = findViewById(R.id.expertMode)
        b.setOnClickListener {
            val intent = Intent(this, main_game::class.java)
            startActivity(intent)
        }
    }
}
