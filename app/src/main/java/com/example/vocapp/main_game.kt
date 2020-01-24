package com.example.vocapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class main_game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        Button btn = (Button)findViewById(R.id.butn1)
    }
}
