package fr.azmobile.feature.album.data.cache.db

import androidx.room.*
import io.reactivex.Single

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Dao
interface ItemDao {

    @Query("SELECT * FROM itementity WHERE albumId IN (:albumIds) LIMIT (:limit) OFFSET (:offset)")
    fun loadAllByAlbumIds(albumIds: IntArray, limit: Int, offset: Int): Single<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg albums: ItemEntity)

    @Delete
    fun delete(item: ItemEntity)
}