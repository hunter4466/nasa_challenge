package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClearFilterBtn(
    action: () -> Unit = {}) {
    TextButton(
        onClick = action,
        modifier = Modifier.padding(16.dp, 0.dp),
        ) {
        Text("Clear Filters")
    }
}