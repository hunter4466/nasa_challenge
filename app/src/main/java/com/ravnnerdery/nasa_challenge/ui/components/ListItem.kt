package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ListItem(
    sol: Long,
    rover: String,
    camera: String,
    imgSrc: String,
) {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(rover)
            Text(camera)
            Text("Sol: $sol")
        }
        Image(
            painter = rememberImagePainter(
                data = imgSrc,
                builder = {
                    transformations(CircleCropTransformation())
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp))
    }
}