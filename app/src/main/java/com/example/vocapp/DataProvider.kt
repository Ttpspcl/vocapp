package com.example.vocapp

import android.util.Log
import java.io.*
import java.nio.charset.Charset
import kotlin.random.Random


class DataProvider {

    companion object {

        private val validWordList = mutableListOf<String>()

        private val easyWordList = mutableListOf<String>()
        private val mediumWordList = mutableListOf<String>()
        private val hardWordList = mutableListOf<String>()

        fun readInputWordFromCSV(inputStream: InputStream) {
            if (easyWordList.isNotEmpty()) return
            try {
                val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
                reader.readLines().forEach {
                    Log.d("Data Provider", it)
                    val tempWordList = it.split(",").toMutableList()
                    if (tempWordList.size > 0 && tempWordList[0].length > 2) easyWordList.add(tempWordList[0])
                    if (tempWordList.size > 1 && tempWordList[1].length > 2) easyWordList.add(tempWordList[1])
                    if (tempWordList.size > 2 && tempWordList[2].length > 2) easyWordList.add(tempWordList[2])
                    if (tempWordList.size > 3 && tempWordList[3].length > 2) mediumWordList.add(tempWordList[3])
                    if (tempWordList.size > 4 && tempWordList[4].length > 2) hardWordList.add(tempWordList[4])
                    if (tempWordList.size > 5 && tempWordList[5].length > 2) hardWordList.add(tempWordList[5])
                    if (tempWordList.size > 6 && tempWordList[6].length > 2) hardWordList.add(tempWordList[6])
                }
                Log.d("Data Provider", "The total easy words is" + easyWordList.size)
                Log.d("Data Provider", "The total medium words is" + mediumWordList.size)
                Log.d("Data Provider", "The total hard words is" + hardWordList.size)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun readValidWordsFromCSV(inputStream: InputStream) {
            if (validWordList.isNotEmpty()) return
            try {
                val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
                reader.readLines().forEach {
                    Log.d("Data Provider", it)
                    val tempWordList = it.split(",").toMutableList()
                    validWordList.addAll(tempWordList)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun getEasyWord() = easyWordList[Random.nextInt(easyWordList.size)]

        fun getMediumWord() = mediumWordList[Random.nextInt(mediumWordList.size)]

        fun getHardWord() = hardWordList[Random.nextInt(hardWordList.size)]

        fun isLetterAVowel(letter: String): Boolean = "AEIOUNS".contains(letter)

        fun validateWord(word: String): Boolean = validWordList.contains(word)
    }

}