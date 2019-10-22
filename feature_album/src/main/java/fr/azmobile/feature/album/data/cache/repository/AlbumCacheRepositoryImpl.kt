package fr.azmobile.feature.album.data.cache.repository

import fr.azmobile.feature.album.data.cache.db.AlbumDao
import fr.azmobile.feature.album.data.cache.db.AlbumEntity
import fr.azmobile.feature.album.data.cache.db.ItemDao
import fr.azmobile.feature.album.data.cache.db.ItemEntity
import io.reactivex.Single

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class AlbumCacheRepositoryImpl(private val albumDao: AlbumDao, private val itemDao: ItemDao): AlbumCacheRepository {

    override fun getAlbumListSize(): Single<Int> {
        return albumDao.getRowCount()
    }

    override fun getAlbumList(): Single<List<AlbumEntity>> {
        return albumDao.getAll()
    }

    override fun getAlbumList(page: Int, itemsPerPage: Int): Single<List<AlbumEntity>> {
        return albumDao.getPage(itemsPerPage, (page - 1) * itemsPerPage)
    }

    override fun insertAlbum(albumEntity: AlbumEntity) {
        albumDao.insertAll(albumEntity)
    }

    override fun getItemListByAlbumId(albumId: Int, page: Int, itemsPerPage: Int): Single<List<ItemEntity>> {
        return itemDao.loadAllByAlbumIds(intArrayOf(albumId), page, itemsPerPage)
    }

    override fun insertItem(itemEntity: ItemEntity) {
        itemDao.insertAll(itemEntity)
    }
}