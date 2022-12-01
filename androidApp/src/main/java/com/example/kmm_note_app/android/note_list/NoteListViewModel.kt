package com.example.kmm_note_app.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_note_app.domain.note.Note
import com.example.kmm_note_app.domain.note.NoteDataSource
import com.example.kmm_note_app.domain.note.SearchNotes
import com.example.kmm_note_app.domain.time.DateTimeUtil
import com.example.kmm_note_app.presentation.VioletHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    //referring any notes is app is kill or any changes made
    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
        //caching all the values
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    //Mock note
    init {
        viewModelScope.launch {
            (1..10).forEach {
                noteDataSource.insertNote(
                    Note(
                        id = null,
                        title = "Note$it",
                        content = "Content$it",
                        colorHex = VioletHex,
                        created = DateTimeUtil.now()
                    )
                )
            }
        }
    }

    //loading our notes, coroutine
    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }

}