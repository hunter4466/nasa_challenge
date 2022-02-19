package com.ravnnerdery.nasa_challenge.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ravnnerdery.domain.models.MarsPhoto
import com.ravnnerdery.nasa_challenge.ui.components.*
import com.ravnnerdery.nasa_challenge.viewModels.ListViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: ListViewModel,
    photoList: List<MarsPhoto>,
    selectedFilter: MutableState<String?>,
    filterList: List<String>,
    imageLoader: ImageLoader,
    loadingState: Boolean,
    noPhotosState: String,
    refreshState: Boolean,
    cardClickEvent: (id: Long) -> Unit = {}
) {
    if (photoList.isEmpty()) {
        if (loadingState) {
            LoadingScreen(imageLoader = imageLoader)
        } else {
            RoverAndSolInputComponent(
                noPhotosState = noPhotosState,
                label = "Find Sol",
                submit = { sol, rover ->
                    viewModel.loadFromApiAndSetIntoDatabase(sol, rover, false)
                }
            )
        }
    } else {
        Column(
            Modifier.background(Color.LightGray)
        ) {
            Card(
                elevation = 16.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    ChipGroup(elements = filterList,
                        selectedItem = selectedFilter.value,
                        onSelectedChanged = {
                            for (elm in filterList) {
                                if (elm == it) {
                                    selectedFilter.value = it
                                    viewModel.filterByCamera(it)
                                }
                            }
                        })
                    Row {
                        ClearFilterBtn(
                            action = {
                                viewModel.clearFilter()
                                selectedFilter.value = null
                            }
                        )
                        RequestNewSolBtn(
                            name = "Request new sol",
                            onClick = {
                                viewModel.requestNewSol()
                            }
                        )
                    }
                }
            }
            SwipeRefresh(
                state = rememberSwipeRefreshState(refreshState),
                onRefresh = {
                    viewModel.refreshData()
                }
            ) {
                LazyColumn {
                    photoList.forEach { elm ->
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp, 6.dp),
                                elevation = 8.dp,
                                shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    bottomEnd = 64.dp,
                                    topEnd = 64.dp,
                                    bottomStart = 8.dp
                                ),
                                onClick = {
                                    cardClickEvent(elm.id)
                                },
                            ) {
                                ListItem(
                                    sol = elm.sol,
                                    rover = elm.rover,
                                    camera = elm.camera,
                                    imgSrc = elm.imgUrl,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
