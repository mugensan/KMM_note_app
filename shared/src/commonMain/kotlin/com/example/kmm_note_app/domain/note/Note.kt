package com.example.kmm_note_app.domain.note

import com.example.kmm_note_app.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created:LocalDateTime
){
    companion object{
        private val colors = listOf(RedOrangeHex, RedPinkHex, VioletHex, BabyBlueHex, LightGreenHex)

        fun generateRandomColor() = colors.random()
    }
}
