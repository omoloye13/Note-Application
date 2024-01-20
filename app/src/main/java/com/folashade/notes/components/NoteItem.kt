package com.folashade.notes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.folashade.notes.models.Note
import com.folashade.notes.navigations.Routes
import com.folashade.notes.view_model.NoteViewModel
import kotlinx.coroutines.delay

//import java.time.Instant
//import java.time.LocalDateTime
//import java.time.ZoneId
//import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(note: Note, navController: NavController) {
    //Dynamically generate and display the current date and  time
//    val timeStamp = Instant.ofEpochMilli(note.time)
//    val currentTime = LocalDateTime.ofInstant(timeStamp, ZoneId.systemDefault())
//    val formattedTime = currentTime.format(DateTimeFormatter.ofPattern(" EEEE MMM, d,yyyy,hh:mm a"))
//
//    val dateStamp = Instant.ofEpochMilli(note.date)
//    val currentDate = LocalDateTime.ofInstant(dateStamp, ZoneId.systemDefault())
//    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("EEEE MMM, d, yyyy"))
    val noteUIModel: NoteViewModel = viewModel()
    val dismissState = rememberSwipeToDismissBoxState()
    val colorToBeShown by animateColorAsState(
        targetValue = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
            Color.Red
        } else {
            Color.White

        }, label = ""

    )
//setting a dynamic coroutine
    var undoDelete by remember { mutableStateOf(false) }
//    if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) {
//        LaunchedEffect(key1 = "deleteNoteItem") {
//            delay(5000)
//            if (!undoDelete) {
//                noteUIModel.deleteNote(note)
//            }
//
//        }


        if (undoDelete) {
            LaunchedEffect(key1 = "undo") {
                dismissState.reset()
                undoDelete = false
            }

        }


        SwipeToDismissBox(state = dismissState,
            backgroundContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorToBeShown)
                        .padding(horizontal = 8.dp)
                ) {
                    Button(
                        onClick = { undoDelete = true },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text("UNDO")
                    }

                }
            }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navController.navigate(Routes.NoteDetails(note.id.toString())) }
            )

            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {


                    Text(
                        text = note.title,
                        fontWeight = FontWeight.Black,
                        maxLines = 2
                    )
                    Text(
                        text = note.content,
                        maxLines = 10,
                        fontWeight = FontWeight.Black

                    )

//                Text(
//                    text = "Added on: $formattedTime",
//                    modifier = Modifier.align(Alignment.End),
//                    color = Color.Blue
//                )
                }

            }
        }

    }
