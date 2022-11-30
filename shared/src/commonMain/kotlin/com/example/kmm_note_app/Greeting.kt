package com.example.kmm_note_app

import database.NoteEntity

class Greeting {

//    private val db = NoteEntity()

    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}