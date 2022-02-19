package com.ravnnerdery.nasa_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun SolTextField(
    text: String,
    changeValue: (String) -> Unit = {},
) {
//
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = text,
        onValueChange = {
            changeValue(it)
        },
        label = { Text("Input sol here") },
    )
}

@Composable
fun RoverAndSolInputComponent(
    noPhotosState: String,
    label: String,
    submit: (String, String) -> Unit
) {
    var sol by rememberSaveable { mutableStateOf("") }
    var selectedItem by rememberSaveable { mutableStateOf("") }
    var notPermitedAction by rememberSaveable { mutableStateOf("") }
    ConstraintLayout {

        val (txtField) = createRefs()

        Column(
            modifier = Modifier.constrainAs(txtField) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/NASA_logo.svg/2449px-NASA_logo.svg.png",
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(8.dp))
            Card(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {

                    RoverSelector(
                        onSelect = {
                            selectedItem = it
                        }
                    )
                    SolTextField(
                        text = sol,
                        changeValue = {
                            sol = it
                        }
                    )
                    if (notPermitedAction.isNotEmpty()) {
                        Text(
                            text = notPermitedAction,
                            color = Color.Red
                        )
                    }
                    if (noPhotosState.isNotEmpty()) {
                        Text(
                            text = noPhotosState,
                            color = Color.Red
                        )
                    }
                    Button(
                        onClick = {
                            if (selectedItem.isNotEmpty()) {
                                submit(sol, selectedItem)
                            } else {
                                notPermitedAction = "Choose a rover to continue"
                            }
                        },
                        modifier = Modifier.padding(0.dp, 16.dp)
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}
