package fr.azmobile.feature.album.presentation.base.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.domain.repository.AlbumRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class AlbumDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val albumRepository: AlbumRepository)
    : DataSource.Factory<Int, AlbumDomainModel>() {

    internal val albumDataSourceLiveData = MutableLiveData<AlbumDataSource>()

    override fun create(): DataSource<Int, AlbumDomainModel> {
        val albumDataSource = AlbumDataSource(albumRepository, compositeDisposable)
        albumDataSourceLiveData.postValue(albumDataSource)
        return albumDataSource
    }
}