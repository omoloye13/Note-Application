package com.folashade.notes.screens


import android.os.Build
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.folashade.notes.components.NoteItem
import com.folashade.notes.navigations.Routes
import com.folashade.notes.ui.theme.NotesTheme
import com.folashade.notes.view_model.NoteViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(navController: NavController) {

    val noteUIModel: NoteViewModel = viewModel()
    val listOfNotes by noteUIModel.getAllNotes().observeAsState(emptyList())


    Scaffold(
        topBar= {
            TopAppBar(
                title = { Text(text = "Note App") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search for Note"
                        )
                    }
                    IconButton(onClick = {}){
//                            IconButton should have an icon in it and by default it does not take note of the color,
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Icon"
                        )
                    }
                }
            )
        },
        content = {paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()

            ){
                //NOTE ITEMS WILL BE HERE
                items(listOfNotes){ note ->
                    key(note) {
                        NoteItem(note = note, navController = navController)

                    }
//                    NoteItem(note = note, navController = navController)


                }
            }

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddNoteRoute) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Note"
                )

            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    NotesTheme {
//        NoteListScreen()

    }
}