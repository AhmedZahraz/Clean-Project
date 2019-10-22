package fr.azmobile.feature.album.data.remote.repository

import fr.azmobile.feature.album.data.remote.LeBonCoinAPI
import fr.azmobile.feature.album.data.remote.model.AlbumDataModel
import io.reactivex.Single

internal class AlbumRemoteRepositoryImpl(
    private val albumRetrofitService: LeBonCoinAPI
) : AlbumRemoteRepository {

    override fun getAlbumItemList(): Single<List<AlbumDataModel>?> {
        return albumRetrofitService.getAlbumList()
    }
}
