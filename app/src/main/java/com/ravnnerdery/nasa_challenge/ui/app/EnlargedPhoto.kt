package com.ravnnerdery.nasa_challenge.ui.app

import androidx.compose.runtime.Composable
import coil.ImageLoader
import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.nasa_challenge.ui.components.EnlargedPhotoListItem
import com.ravnnerdery.nasa_challenge.ui.components.LoadingScreen

@Composable
fun EnlargedPhoto(
    photoState: MarsPhoto,
    loadingState: Boolean,
    imageLoader: ImageLoader,
) {
    if(loadingState){
        LoadingScreen(imageLoader = imageLoader)
    } else {
        EnlargedPhotoListItem(photoState.imgUrl)
    }
}