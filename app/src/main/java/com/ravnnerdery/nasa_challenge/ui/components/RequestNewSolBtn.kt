package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun RequestNewSolBtn(
    name: String,
    onClick: () -> Unit = {}
) {
    TextButton(
        onClick = onClick
    ) {
        Text(name)
    }
}
