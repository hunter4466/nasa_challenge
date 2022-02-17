package com.ravnnerdery.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ravnnerdery.data.mappers.DomainMapper
import com.ravnnerdery.domain.models.MarsPhoto

@Entity(tableName = "photo_table")
class MarsPhotoEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "sol")
    var sol: Long,
    @ColumnInfo(name = "rover")
    var rover: String,
    @ColumnInfo(name = "camera")
    var camera: String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String,
): DomainMapper<MarsPhoto> {
    override fun mapToDomainModel(): MarsPhoto = MarsPhoto(
        id, sol, rover, camera, imgUrl
    )
}