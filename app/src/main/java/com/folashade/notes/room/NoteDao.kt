package com.folashade.notes.room

import androidx.lifecycle.LiveData
import com.folashade.notes.models.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDao {
    @Insert
  suspend  fun saveNotes(note: Note)



  @Query("select * from notes")
  fun fetchNotes(): LiveData<List<Note>>


  @Query("select* from notes WHERE id = :noteId")
  fun fetchSingleNoteDetails(noteId :String):LiveData<Note>

  //delete query
  @Delete
  suspend fun deleteNote(note : Note)

  ////update query
  @Update
  suspend fun updateNote(note : Note)
}


