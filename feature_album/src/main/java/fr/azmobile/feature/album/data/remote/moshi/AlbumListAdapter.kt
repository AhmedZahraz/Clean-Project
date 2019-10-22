package fr.azmobile.feature.album.data.remote.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import fr.azmobile.feature.album.data.cache.db.AlbumEntity
import fr.azmobile.feature.album.data.cache.db.ItemEntity
import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepository
import fr.azmobile.feature.album.data.remote.model.AlbumDataModel

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class AlbumListAdapter(private val albumCacheRepository: AlbumCacheRepository) {

    @FromJson
    internal fun fromJson(
        reader: JsonReader
    ): List<AlbumDataModel> {

        reader.beginArray()
        while (reader.hasNext()) {
            var albumId = 0
            var id = 0
            var title = ""
            var imageURL = ""
            var thumbnailURL = ""

            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "albumId" -> albumId = reader.nextInt()
                    "id" -> id = reader.nextInt()
                    "title" -> title = reader.nextString()
                    "url" -> imageURL = reader.nextString()
                    "thumbnailUrl" -> thumbnailURL = reader.nextString()
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
            albumCacheRepository.insertAlbum(AlbumEntity(albumId))
            albumCacheRepository.insertItem(
                ItemEntity(
                    id = id,
                    albumId = albumId,
                    title = title,
                    imageURL = imageURL,
                    thumbnailURL = thumbnailURL
                )
            )
        }
        reader.endArray()

        return emptyList()
    }
}