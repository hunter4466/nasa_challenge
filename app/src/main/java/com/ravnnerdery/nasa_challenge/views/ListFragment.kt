package com.ravnnerdery.nasa_challenge.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ravnnerdery.nasa_challenge.ui.components.ListItem
import com.ravnnerdery.nasa_challenge.ui.theme.Nasa_challengeTheme
import com.ravnnerdery.nasa_challenge.ui.components.ChipGroup
import com.ravnnerdery.nasa_challenge.viewModels.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        val selectedFilter: MutableState<String?> = mutableStateOf(null)

        view.apply {
            setContent {
                Nasa_challengeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val photoList by listViewModel.observablePhotoList.collectAsState(initial = emptyList())
                        val filterList by listViewModel.observableFilterList.collectAsState(initial = emptyList())
                        LazyColumn {
                            item {
                                ChipGroup(elements = filterList,
                                    selectedItem = selectedFilter.value,
                                    onSelectedChanged = {
                                        for (elm in filterList) {
                                            if (elm == it) {
                                                selectedFilter.value = it
                                                listViewModel.filterByCamera(it)
                                            }
                                        }

                                    })
                            }
                            photoList.forEach { elm ->
                                Log.v("DATABASE INFO", "id: ${elm.id}, sol:${elm.sol}, rover: ${elm.rover}, camera: ${elm.camera}, url: ${elm.imgUrl}")
                                item {
                                    Button(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            requireView().findNavController().navigate(
                                                ListFragmentDirections.actionListFragmentToPhotoFragment(
                                                    elm.id
                                                )
                                            )
                                        },
                                        contentPadding = PaddingValues(
                                            start = 20.dp,
                                            top = 12.dp,
                                            end = 20.dp,
                                            bottom = 12.dp
                                        )
                                    ) {
                                        ListItem(
                                            sol = elm.sol,
                                            rover = elm.rover,
                                            camera = elm.camera,
                                            imgSrc = elm.imgUrl
                                        )
                                    }

                                }
                            }

                        }

                    }
                }
            }
        }
        return view
    }
}