package com.example.vocapp

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.view.children
import androidx.core.view.isVisible
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_game.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log


class MainGameActivity : AppCompatActivity() {

    companion object {
        const val WORD_KEY = "word_key"
        const val EASY_KEY = "easy_key"
        const val MID_KEY = "mid_key"
        const val HARD_KEY = "hard_key"
    }

    private lateinit var word: String
    private lateinit var diff: String
    private var toast: Toast? = null

    private val diatA = arrayOf("à", "a", "á")
    private val diatE = arrayOf("è", "e", "é", "ẹ̀", "ẹ", "ẹ́")
    private val diatI = arrayOf("ì", "i", "í")
    private val diatO = arrayOf("ò", "o", "ó", "ọ̀", "ọ", "ọ́")
    private val diatU = arrayOf("ù", "u", "ú")
    private val diatN = arrayOf("ǹ", "n", "ń")
    private val diatS = arrayOf("s", "ṣ")

    private var alreadyWords = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        diff = intent.getStringExtra(WORD_KEY)!!

        setupScreen()

        deleteButton.setOnClickListener {
            deleteLetter(textView9.text.toString())
        }

        tryNewWordButton.setOnClickListener {
            resetScreen()
        }

        checkWord.setOnClickListener { validateInput() }
    }

    fun setupScreen() {
       word =  when(diff) {
           EASY_KEY -> DataProvider.getEasyWord()
           MID_KEY -> DataProvider.getMediumWord()
           HARD_KEY -> DataProvider.getHardWord()
           else -> ""
       }
        word = word.shuffle()
        specialLettersContainerLinearLayout.visibility = View.INVISIBLE
        for (char in word.toCharArray()) { addLetter(char) }
    }

    private fun validateInput() {
        val word = textView9.text.toString()
        if (word.isNotEmpty()) {
            if (DataProvider.validateWord(word)) {
                if (alreadyWords.contains(word)) {
                    showToast("Word has been validated already")
                } else {
                    showToast("Word Correct")
                    val scoreBoard = textView7.text.toString()
                    var score = scoreBoard.substring(8).toInt()
                    alreadyWords.add(word)
                    score++
                    textView7.text = "SCORE : $score"

                    while(textView9.text.isNotEmpty()) deleteLetter(textView9.text.toString())
                }
            } else {
                showToast("Invalid Word Formed")
            }
        } else {
            showToast("Cannot Validate Empty Word")
        }
    }

    private fun addLetter(letter: Char) {
        val letterView = SingleLetterView(this, letter.toString())

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
                addSpecialChars(parentView, char.toString())
                    }
            "E".elementAt(0) -> for(char in diatE) {
                addSpecialChars(parentView, char.toString())
            }
            "I".elementAt(0) -> for(char in diatI) {
                addSpecialChars(parentView, char.toString())
            }
            "O".elementAt(0) -> for(char in diatO) {
            addSpecialChars(parentView, char.toString())
        }
            "U".elementAt(0) -> for(char in diatU) {
            addSpecialChars(parentView, char.toString())
        }
            "S".elementAt(0) -> for(char in diatS) {
            addSpecialChars(parentView, char.toString())
        }
            "N".elementAt(0) -> for(char in diatN) {
            addSpecialChars(parentView, char.toString())
        }
            else -> println("Number is not between 1 and 3")
        }
       }

    fun addSpecialChars(parentView: View, letter: String) {
        val specialLetterView = SingleLetterView(this, letter)

        specialLetterView.setOnClickListener{
            textView9.text = String.format("%s%s", textView9.text.toString(), (it as SingleLetterView).getLetter())
            it.visibility = View.GONE
            parentView.visibility = View.GONE
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
            var newWord = word
            while (!newWord.toCharArray()[newWord.length - 1].isAlphabet()) {
                newWord = newWord.substring(0, newWord.length - 1)
            }
            textView9.text = newWord.substring(0, newWord.length - 1)
            Log.d("Word", newWord)
            addLetter(newWord.toCharArray()[newWord.length - 1])
        }
    }

    private fun resetScreen() {
        textView9.text = ""
        lettersContainerLinearLayout.removeAllViews()
        setupScreen()
    }

    private fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
