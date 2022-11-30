package com.example.kmm_note_app.data.local

import com.example.kmm_note_app.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver():SqlDriver{
        return NativeSqliteDriver(NoteDatabase.Schema,"note.db")
    }
}