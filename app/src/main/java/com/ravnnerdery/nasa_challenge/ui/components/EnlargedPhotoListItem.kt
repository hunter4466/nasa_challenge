package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
fun EnlargedPhotoListItem(imgSrc: String) {
    Image(

        painter = rememberImagePainter(
            data = imgSrc,
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxHeight()
    )
}
