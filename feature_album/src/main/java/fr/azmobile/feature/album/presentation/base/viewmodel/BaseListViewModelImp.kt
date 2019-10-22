package fr.azmobile.feature.album.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.azmobile.feature.album.presentation.base.model.State
import fr.azmobile.feature.album.presentation.base.paging.BaseDataSource
import fr.azmobile.feature.album.presentation.base.paging.BaseDataSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

open class BaseListViewModelImp<T>(getList: (page: Int, itemsPerPage: Int) -> Single<List<T>>) :
    ViewModel(),
    BaseListViewModel<T> {

    private var albumList: LiveData<PagedList<T>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val albumDataSourceFactory: BaseDataSourceFactory<T>

    init {
        albumDataSourceFactory = BaseDataSourceFactory(compositeDisposable, getList)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        albumList = LivePagedListBuilder<Int, T>(albumDataSourceFactory, config).build()
    }

    override fun getAlbumLiveData(): LiveData<PagedList<T>> {
        return albumList
    }

    override fun getState(): LiveData<State> = Transformations.switchMap<BaseDataSource<T>,
            State>(albumDataSourceFactory.dataSourceLiveData, BaseDataSource<T>::state)

    override fun retry() {
        albumDataSourceFactory.dataSourceLiveData.value?.retry()
    }

    override fun listIsEmpty(): Boolean {
        return albumList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}