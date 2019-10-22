package fr.azmobile.feature.album.data.remote.repository

import fr.azmobile.feature.album.data.remote.model.AlbumDataModel
import io.reactivex.Single

internal interface AlbumRemoteRepository {

    fun getAlbumItemList(): Single<List<AlbumDataModel>?>
}
