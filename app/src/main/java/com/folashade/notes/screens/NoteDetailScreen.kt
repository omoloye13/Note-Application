package com.folashade.notes.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.folashade.notes.models.Note
import com.folashade.notes.view_model.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun NoteDetailScreen(navController : NavController, noteId:String) {


    //creating an instance of the note view model in the note detail screen so we can access a single note
    val noteUIModel: NoteViewModel = viewModel()
    //accessing the getNoteDetails in the note View model here so as to display the details of each note
    val singleNoteDetail by noteUIModel.getNoteDetails(noteId).observeAsState()
    var isEditMode by rememberSaveable{ mutableStateOf(false) }
    var title by rememberSaveable{mutableStateOf( "")}
    var content by rememberSaveable{mutableStateOf( "")}

    fun EditNote(){
        isEditMode = true
        title = singleNoteDetail?.title ?: ""
        content = singleNoteDetail?.content ?: ""
    }
  Scaffold (
   topBar= {
    TopAppBar(
     title = { Text(text = "Note Details") },
     colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = Color.White,
      actionIconContentColor = Color.White
     ),
     actions = {
// conditionally rendering the edit icon button and save icon button
         if(isEditMode){
             //show save icon
             IconButton(onClick = {
                 val updatedNote: Note = singleNoteDetail!!.copy(
                     title=title,
                     content=content
                 )
                 noteUIModel.updateNote(updatedNote)
                 isEditMode = false
//          navController.popBackStack()
             }){
                 Icon(
                     imageVector = Icons.Default.CheckCircle,
                     contentDescription = "Save Note"
                 )
             }
         }else{
             //show edit icon
             IconButton(onClick = {
                 EditNote()

             }){
                 Icon(
                     imageVector = Icons.Default.Edit,
                     contentDescription = "Edit Note"
                 )
             }
         }



      IconButton(onClick = {
          noteUIModel.deleteNote(singleNoteDetail!!)

          navController.popBackStack()
      }){
//  IconButton should have an icon in it and by default it does not take note of the color,
       Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = "Delete Note"
       )
      }
     }
    )
   },
   content = {paddingValues ->
    Column(
     modifier = Modifier
         .padding(paddingValues)
         .fillMaxSize()

    ){
        if (isEditMode){
            TextField(

                value = title ?:  "",
            onValueChange = {value -> title = value},
                modifier = Modifier.fillMaxWidth(),

            )

            TextField(value = content ?: "",
                onValueChange = {value -> content = value},
                        modifier = Modifier.fillMaxWidth(),


            )
        }else{

            Text(singleNoteDetail?.title ?: "", modifier = Modifier.fillMaxWidth().clickable{ EditNote()})
            Text(singleNoteDetail?.content ?: "", modifier = Modifier.fillMaxWidth().clickable{EditNote()})
        }

    }

   }

  )
}


