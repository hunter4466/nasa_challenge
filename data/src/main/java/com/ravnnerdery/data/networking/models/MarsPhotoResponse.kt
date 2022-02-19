package com.ravnnerdery.data.networking.models

class MarsPhotoResponse(
    var id: Long,
    var sol: Long,
    var camera: CameraResponse,
    var img_src: String,
    var rover: RoverResponse,
)