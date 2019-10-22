package fr.azmobile.feature.album.data.cache.db

import androidx.room.*
import io.reactivex.Single

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albumentity")
    fun getAll(): Single<List<AlbumEntity>>

    @Query("SELECT * FROM albumentity LIMIT (:limit) OFFSET (:offset)")
    fun getPage(limit: Int, offset: Int): Single<List<AlbumEntity>>

    @Query("SELECT COUNT(id) FROM albumentity")
    fun getRowCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg albums: AlbumEntity)

    @Delete
    fun delete(album: AlbumEntity)
}