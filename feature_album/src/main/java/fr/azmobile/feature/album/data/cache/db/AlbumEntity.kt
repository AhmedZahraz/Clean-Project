package fr.azmobile.feature.album.data.cache.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Entity
data class AlbumEntity(
    @PrimaryKey val id: Int
)