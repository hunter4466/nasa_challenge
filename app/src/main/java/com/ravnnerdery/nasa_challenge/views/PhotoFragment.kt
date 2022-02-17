package com.ravnnerdery.nasa_challenge.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.nasa_challenge.ui.components.EnlargedPhotoListItem
import com.ravnnerdery.nasa_challenge.viewModels.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private val photoViewModel: PhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        val args = PhotoFragmentArgs.fromBundle(requireArguments())
        val enlargedPhoto = photoViewModel.enlargedPhoto(args.imgId)
        view.setContent {
            val enlargedPhotoState by enlargedPhoto.collectAsState(initial = MarsPhoto(0,0,"null","null","null"))
            EnlargedPhotoListItem(enlargedPhotoState.imgUrl)
        }
    return view
}

}

