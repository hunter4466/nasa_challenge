package com.ravnnerdery.nasa_challenge.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoverOption(
    name: String,
    selected: Boolean,
    onSelect: (String) -> Unit = {},
) {
    Row(modifier = Modifier.padding(4.dp)) {
        RadioButton(
            selected = selected,
            onClick = { onSelect(name) })
        Text(
            text = name
        )
    }
}

@Composable
fun RoverSelector(
    onSelect: (String) -> Unit = {},
) {
    var selectedItem by rememberSaveable { mutableStateOf("") }
    val radioOptions = listOf("curiosity", "opportunity", "perseverance", "spirit")

    Column(
        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
    ) {
        Text(
            text = "Choose a rover and sol:",
            fontSize = 24.sp,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
        )
        radioOptions.forEach { elm ->
            Log.v("COMPONENT RENDER", "TRYING TO RENDER COMPONENT: $elm")
            RoverOption(
                name = elm,
                selected = elm == selectedItem,
                onSelect = {
                    selectedItem = it
                    onSelect(it)
                }
            )
        }
    }
}