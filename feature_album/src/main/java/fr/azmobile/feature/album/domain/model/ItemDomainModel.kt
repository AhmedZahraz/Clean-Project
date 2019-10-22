package fr.azmobile.feature.album.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ZAHRAZ Ahmed on 2019-10-20.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Parcelize
data class ItemDomainModel(
    val id: Int,
    val albumId: Int,
    val title: String?,
    val imageURL: String?,
    val thumbnailURL: String?
) : Parcelable