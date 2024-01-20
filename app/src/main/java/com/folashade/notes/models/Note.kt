package com.folashade.notes.models
import androidx.room.Entity
import androidx.room.PrimaryKey

//defining a table to be held by the database using entity
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate= true)
    val id : Int  = 0,
    val title : String,
    val content: String,
//    var time : Long,
//    val date : Long,
)
