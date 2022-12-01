package com.example.kmm_note_app.domain.note

import com.example.kmm_note_app.domain.time.DateTimeUtil
import kotlinx.datetime.DateTimePeriod

class SearchNotes {

    fun execute(notes: List<Note>,query: String): List<Note> {
        if (query.isBlank()){
            return notes
        }
        return notes.filter{
            it.title.trim().lowercase().contains(query.lowercase())||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            //Later date on top of list
            DateTimeUtil.toEpochMillis(it.created)
        }
    }

}