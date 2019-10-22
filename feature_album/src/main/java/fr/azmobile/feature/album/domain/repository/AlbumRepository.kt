package fr.azmobile.feature.album.domain.repository

import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import io.reactivex.Single

interface AlbumRepository {
    fun getAlbumList(page: Int, itemsPerPage: Int = 10): Single<List<AlbumDomainModel>>
    fun getItemList(albumId: Int, page: Int, itemsPerPage: Int = 10): Single<List<ItemDomainModel>>
}
