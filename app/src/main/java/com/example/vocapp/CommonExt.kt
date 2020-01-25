package com.example.vocapp

fun String.shuffle(): String = this.toCharArray().toList().shuffled().joinToString(separator = "")
