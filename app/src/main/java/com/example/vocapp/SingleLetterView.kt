package com.example.vocapp

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class SingleLetterView(
    context: Context,
    letter: Char
) : CardView(context) {

    private var letter: Char

    init {
        View.inflate(context, R.layout.single_letter_view, this)
        val letterTextView: TextView = findViewById(R.id.singleTextView)
        letterTextView.text = letter.toString()
        this.letter = letter

        if(DataProvider.isLetterAVowel(letter)) {
            activatePopup()
        }
    }

    fun activatePopup() {
        //TODO Write popup Code
    }

    fun getLetter(): Char = letter

}