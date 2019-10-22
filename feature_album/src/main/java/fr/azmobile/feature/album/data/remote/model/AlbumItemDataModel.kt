package fr.azmobile.feature.album.data.remote.model

import com.squareup.moshi.Json

/*{
    "albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
}*/

internal data class AlbumDataModel(
    val albumId: Int,
    val id: String,
    val title: String,
    @field:Json(name = "url") val imageURL: String,
    @field:Json(name = "thumbnailUrl") val thumbnailURL: String
)
