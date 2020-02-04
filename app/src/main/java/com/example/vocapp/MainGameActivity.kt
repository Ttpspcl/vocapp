package com.example.vocapp

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.view.children
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main_game.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast


class MainGameActivity : AppCompatActivity() {

    companion object {
        const val WORD_KEY = "word_key"
    }

    private lateinit var word: String
    private var diatA = "AAAà".toCharArray()
    private var diatE = "EEEE".toCharArray()
    private var diatI = "III".toCharArray()
    private var diatO = "OOO".toCharArray()
    private var diatU = "UUU".toCharArray()
    private var diatN = "NNN".toCharArray()
    private var diatS = "S".toCharArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        word = intent.getStringExtra(WORD_KEY)!!

        word = word.shuffle()
//        specialLettersContainerLinearLayout.focusable = true;
        specialLettersContainerLinearLayout.visibility = View.INVISIBLE
        for (char in word.toCharArray()) { addLetter(char) }

        deleteButton.setOnClickListener {
            deleteLetter(textView9.text.toString())
        }
    }

    fun addLetter(letter: Char) {
        val letterView = SingleLetterView(this, letter)

        letterView.setOnClickListener {
                if(!("AEIOUSN").contains(letter.toUpperCase()))
                {
                    textView9.text = String.format("%s%s", textView9.text.toString(), (it as SingleLetterView).getLetter())
                    it.visibility = View.GONE
                }
                else {
                    popupForSpecialCharacters(it, letter)
                }
        }
        lettersContainerLinearLayout.addView(letterView)
    }
    
    //popup for characaters with diacristics
    fun popupForSpecialCharacters(parentView : View, letter: Char) {
        for( child in specialLettersContainerLinearLayout.children ){
            child.visibility = View.GONE
        }
        specialLettersContainerLinearLayout.visibility = View.VISIBLE

        when(letter.toUpperCase() ) {
            "A".elementAt(0) -> for(char in diatA) {
                addSpecialChars(parentView, char)
                    }
            "E".elementAt(0) -> for(char in diatE) {
                addSpecialChars(parentView, char)
            }
            "I".elementAt(0) -> for(char in diatI) {
                addSpecialChars(parentView, char)
            }
            "O".elementAt(0) -> for(char in diatO) {
            addSpecialChars(parentView, char)
        }
            "U".elementAt(0) -> for(char in diatU) {
            addSpecialChars(parentView, char)
        }
            "S".elementAt(0) -> for(char in diatS) {
            addSpecialChars(parentView, char)
        }
            "N".elementAt(0) -> for(char in diatN) {
            addSpecialChars(parentView, char)
        }
            else -> println("Number is not between 1 and 3")
        }
       }

    fun addSpecialChars(parentView: View, letter: Char) {
        val specialLetterView = SingleLetterView(this, letter)

        specialLetterView.setOnClickListener{
            textView9.text = String.format("%s%s", textView9.text.toString(), (it as SingleLetterView).getLetter())
            it.visibility = View.GONE
            parentView.visibility = View.GONE;
            specialLettersContainerLinearLayout.visibility = View.INVISIBLE
        }

        specialLettersContainerLinearLayout.addView(specialLetterView)

    }

//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        val viewRect: Rect = Rect()
//        specialLettersContainerLinearLayout.getGlobalVisibleRect(viewRect)
//        if (specialLettersContainerLinearLayout.isVisible ) {
//
//
//            if( !(viewRect.contains(ev.getX() as Int, ev.getY() as Int)) ) {
//                specialLettersContainerLinearLayout.visibility = View.INVISIBLE
//                return true
//            }
//        }
//        return super.dispatchTouchEvent(ev)
//    }
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
