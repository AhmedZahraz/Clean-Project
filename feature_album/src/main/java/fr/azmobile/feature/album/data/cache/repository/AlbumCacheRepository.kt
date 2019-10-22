package fr.azmobile.feature.album.data.cache.repository

import fr.azmobile.feature.album.data.cache.db.AlbumEntity
import fr.azmobile.feature.album.data.cache.db.ItemEntity
import io.reactivex.Single

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

interface AlbumCacheRepository {
    fun getAlbumListSize(): Single<Int>
    fun getAlbumList(): Single<List<AlbumEntity>>
    fun getAlbumList(page: Int, itemsPerPage: Int): Single<List<AlbumEntity>>
    fun insertAlbum(albumEntity: AlbumEntity)
    fun getItemListByAlbumId(albumId: Int, page: Int, itemsPerPage: Int): Single<List<ItemEntity>>
    fun insertItem(itemEntity: ItemEntity)
}
