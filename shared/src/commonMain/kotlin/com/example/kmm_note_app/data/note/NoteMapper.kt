package com.example.kmm_note_app.data.note

import com.example.kmm_note_app.domain.note.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

//Mapping note
fun NoteEntity.toNote(): Note{
    return Note(
        id = id,
        title = title,
        content =content,
        colorHex = colorHex,
        created = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault() )

    )
}