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
        val intent = Intent(this, MainGameActivity::class.java)

        b = findViewById(R.id.easyMode)
        b.setOnClickListener {
            intent.putExtra(MainGameActivity.WORD_KEY, MainGameActivity.EASY_KEY)
            startActivity(intent)
        }
        b = findViewById(R.id.intermediateMode)
        b.setOnClickListener {
            intent.putExtra(MainGameActivity.WORD_KEY, MainGameActivity.MID_KEY)
            startActivity(intent)
        }
        b = findViewById(R.id.expertMode)
        b.setOnClickListener {
            intent.putExtra(MainGameActivity.WORD_KEY, MainGameActivity.HARD_KEY)
            startActivity(intent)
        }
    }
}
