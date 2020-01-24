package com.example.vocapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var b: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream: InputStream = resources.openRawResource(R.raw.syllables)
        DataProvider.readWordsFromCSV(inputStream)

        b = findViewById(R.id.startGameButton)
        b.setOnClickListener {
            val intent = Intent(this, Seun2Activity::class.java)
            startActivity(intent)
        }
    }
}