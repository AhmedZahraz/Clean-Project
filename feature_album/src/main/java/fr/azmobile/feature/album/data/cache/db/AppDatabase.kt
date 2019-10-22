package fr.azmobile.feature.album.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Database(entities = [AlbumEntity::class, ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun itemDao(): ItemDao
}