package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.rememberImagePainter

@Composable
fun LoadingScreen(imageLoader: ImageLoader) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        val (image) = createRefs()
        Image(
            painter = rememberImagePainter(
                data = "https://c.tenor.com/I6kN-6X7nhAAAAAi/loading-buffering.gif",
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .padding(8.dp)
                .constrainAs(image){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}
