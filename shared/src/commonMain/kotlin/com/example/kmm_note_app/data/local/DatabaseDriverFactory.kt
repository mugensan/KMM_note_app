package com.example.kmm_note_app.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver():SqlDriver

}