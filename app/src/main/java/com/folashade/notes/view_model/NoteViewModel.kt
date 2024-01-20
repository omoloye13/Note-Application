package com.folashade.notes.view_model


import androidx.lifecycle.AndroidViewModel
import com.folashade.notes.room.DatabaseConfig
import com.folashade.notes.models.Note
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel( applications: Application): AndroidViewModel(applications) {

    //Calling the save function of the database
   private var noteDB = DatabaseConfig.getInstance(applications)
    fun saveNotes(title:String, content:String){

        if (title.isNullOrEmpty() && content.isNullOrEmpty() )return
        // Creating a Note Instance
//        val currentTimeMillis = System.currentTimeMillis()
        val note = Note(
            title = title,
            content = content,
//            time = System.currentTimeMillis(),
//            date = getStartOfDay(currentTimeMillis),
        )
        //Saving The Note to the Database
        viewModelScope.launch{
            noteDB.noteDao().saveNotes(note)

        }
    }

    //function to getAll Notes from the database to display on the UI
    fun  getAllNotes(): LiveData<List<Note>> {
        return noteDB.noteDao().fetchNotes()
    }

    // function to get the details of the note
    fun getNoteDetails(noteId:String):LiveData<Note>{
        return noteDB.noteDao().fetchSingleNoteDetails(noteId)
    }
      // function to delete the note
    fun deleteNote(note : Note) {
        viewModelScope.launch {
            noteDB.noteDao().deleteNote(note)
        }
    }


    //function to update the note when it is edited

    fun updateNote(note:Note){
        viewModelScope.launch{
            noteDB.noteDao().updateNote(note)
        }
    }

}