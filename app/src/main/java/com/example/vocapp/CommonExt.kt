package com.example.vocapp

fun String.shuffle(): String = this.toCharArray().toList().shuffled().joinToString(separator = "")

fun Char.isAlphabet(): Boolean = this in 'a'..'z' || this in 'A'..'Z'
