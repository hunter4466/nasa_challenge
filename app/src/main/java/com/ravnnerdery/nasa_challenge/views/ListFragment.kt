package com.ravnnerdery.nasa_challenge.views

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.ravnnerdery.nasa_challenge.ui.app.MainScreen
import com.ravnnerdery.nasa_challenge.ui.theme.Nasa_challengeTheme
import com.ravnnerdery.nasa_challenge.viewModels.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        val selectedFilter: MutableState<String?> = mutableStateOf(null)

        view.apply {
            val imageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder(context))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()
            setContent {
                Nasa_challengeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val photoList by listViewModel.observablePhotoList.collectAsState(initial = emptyList())
                        val filterList by listViewModel.observableFilterList.collectAsState(initial = emptyList())
                        val loadingState by listViewModel.observableLoadingState.collectAsState(
                            initial = false
                        )
                        val noPhotosState by listViewModel.observableNoPhotosFoundState.collectAsState(
                            initial = ""
                        )
                        val refreshState by listViewModel.observableRefreshState.collectAsState(
                            initial = false
                        )
                        MainScreen(
                            viewModel = listViewModel,
                            photoList = photoList,
                            selectedFilter = selectedFilter,
                            filterList = filterList,
                            imageLoader = imageLoader,
                            loadingState = loadingState,
                            noPhotosState = noPhotosState,
                            refreshState = refreshState,
                            cardClickEvent = {
                                requireView().findNavController().navigate(
                                    ListFragmentDirections.actionListFragmentToPhotoFragment(
                                        it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
        return view
    }
}