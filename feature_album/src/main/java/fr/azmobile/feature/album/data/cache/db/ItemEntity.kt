package fr.azmobile.feature.album.data.cache.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

/*{
    "albumId": 46,
    "id": 2292,
    "title": "exercitationem consequatur est aliquam eveniet corrupti voluptate voluptatem rerum",
    "url": "https://via.placeholder.com/600/6a6c02",
    "thumbnailUrl": "https://via.placeholder.com/150/6a6c02"
}*/

@Entity(foreignKeys = [ForeignKey(entity = AlbumEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("albumId"),
    onDelete = ForeignKey.CASCADE)]
)
data class ItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "albumId") val albumId: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val imageURL: String?,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailURL: String?
)