package com.example.kmm_note_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform