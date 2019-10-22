package fr.azmobile.feature.album.data.remote

import fr.azmobile.feature.album.data.remote.model.AlbumDataModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by ahmed on 17/04/2018.
 */
internal interface LeBonCoinAPI {

    @GET("technical-test.json")
    fun getAlbumList(): Single<List<AlbumDataModel>?>
}