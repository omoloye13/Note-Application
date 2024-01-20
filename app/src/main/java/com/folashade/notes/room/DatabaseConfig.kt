package com.folashade.notes.room

import android.content.Context
import com.folashade.notes.models.Note
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//creating an instance of the database note this is not a single instance of it yet
object DatabaseConfig {

    fun getInstance (context : Context): NoteDatabase{
        val noteDB = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).build()
        return noteDB

    }



}
@Database(entities = [Note::class], version = 1)
abstract  class NoteDatabase : RoomDatabase(){

    abstract fun noteDao():NoteDao
}