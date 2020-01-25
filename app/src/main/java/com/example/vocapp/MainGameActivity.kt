package com.example.vocapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main_game.*

class MainGameActivity : AppCompatActivity() {

    companion object {
        const val WORD_KEY = "word_key"
    }

    private lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        word = intent.getStringExtra(WORD_KEY)!!

        word = word.shuffle()

        for (char in word.toCharArray()) { addLetter(char) }

        deleteButton.setOnClickListener {
            deleteLetter(textView9.text.toString())
        }
    }

    fun addLetter(letter: Char) {
        val letterView = SingleLetterView(this, letter)
        letterView.setOnClickListener {
            textView9.text = String.format("%s%s", textView9.text.toString(), (it as SingleLetterView).getLetter())
            it.visibility = View.GONE
        }
        lettersContainerLinearLayout.addView(letterView)
    }

    override fun onBackPressed() {
        val word = textView9.text.toString()
        if (word.isNotEmpty()) {
            deleteLetter(word)
        } else {
            super.onBackPressed()
        }
    }

    private fun deleteLetter(word: String) {
        if (word.isNotEmpty()) {
            val wordToRemove = word.toCharArray()[word.length - 1]
            textView9.text = word.substring(0, word.length - 1)
            addLetter(wordToRemove)
        }
    }
}