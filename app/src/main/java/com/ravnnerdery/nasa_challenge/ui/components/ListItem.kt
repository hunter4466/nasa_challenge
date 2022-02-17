package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ListItem(sol: Long, rover: String, camera: String, imgSrc: String) {
    FlowRow {
        Column {
            Text(rover)
            Text(camera)
            Text(sol.toString())
        }
        Image(
            painter = rememberImagePainter(
                data = imgSrc,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    }
}