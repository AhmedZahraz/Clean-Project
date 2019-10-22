package fr.azmobile.feature.album.domain.repository

import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepository
import fr.azmobile.feature.album.data.remote.repository.AlbumRemoteRepository
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import io.reactivex.Single
import io.reactivex.SingleEmitter

internal class AlbumRepositoryImpl(
    private val albumRemoteRepository: AlbumRemoteRepository,
    private val albumCacheRepository: AlbumCacheRepository
) : AlbumRepository {

    override fun getAlbumList(page: Int, itemsPerPage: Int): Single<List<AlbumDomainModel>> {
        fun getAlbumListFromCache(singleEmitter: SingleEmitter<List<AlbumDomainModel>>) =
            albumCacheRepository.getAlbumList(page, itemsPerPage)
                .map { list ->
                    list.map { AlbumDomainModel(it.id) }
                }.subscribe { t1, t2 ->
                    if (t2 != null) {
                        singleEmitter.onError(t2)
                    } else {
                        singleEmitter.onSuccess(t1)
                    }
                }

        return Single.create { singleEmitter ->
            albumCacheRepository.getAlbumListSize().subscribe { size, error ->
                if (error != null) {
                    singleEmitter.onError(error)
                } else if (size > 0) {
                    getAlbumListFromCache(singleEmitter)
                } else {
                    albumRemoteRepository.getAlbumItemList().subscribe { _, remoteError ->
                        if (remoteError != null) {
                            singleEmitter.onError(remoteError)
                        } else {
                            getAlbumListFromCache(singleEmitter)
                        }
                    }
                }
            }
        }
    }

    override fun getItemList(
        albumId: Int,
        page: Int,
        itemsPerPage: Int
    ): Single<List<ItemDomainModel>> {
        return albumCacheRepository.getItemListByAlbumId(albumId, page, itemsPerPage)
            .map { list ->
                list.map {
                    ItemDomainModel(
                        it.id,
                        it.albumId,
                        it.title,
                        it.imageURL,
                        it.thumbnailURL
                    )
                }
            }
    }
}
