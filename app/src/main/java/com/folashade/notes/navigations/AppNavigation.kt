package com.folashade.notes.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.folashade.notes.screens.AddNoteScreen
import com.folashade.notes.screens.NoteDetailScreen
import com.folashade.notes.screens.NoteListScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.NoteListRoute
    ){
        composable (Routes.NoteListRoute){
            NoteListScreen(navController)
        }
        //AddNote Screen
        composable(Routes.AddNoteRoute){
            AddNoteScreen(navController)
        }
        //NoteDetails Route
        composable("note-details/{noteId}"){
            NoteDetailScreen(
                navController = navController,
                noteId = it.arguments!!.getString("noteId")!!
            )

        }
    }
}
 object Routes {
     val NoteListRoute = "note-List"
     val AddNoteRoute = "add-note"
//     val NoteDetailRoute = "note-details"

     fun NoteDetails(noteId:String):String{
         return "note-details/$noteId"


     }
 }