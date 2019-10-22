package fr.azmobile.feature.album.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import fr.azmobile.feature.album.presentation.base.model.State

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

internal interface BaseListViewModel<T> {
    fun getAlbumLiveData(): LiveData<PagedList<T>>
    fun retry()
    fun getState(): LiveData<State>
    fun listIsEmpty(): Boolean
}