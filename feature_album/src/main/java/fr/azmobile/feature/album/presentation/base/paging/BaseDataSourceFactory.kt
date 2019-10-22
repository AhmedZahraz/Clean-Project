package fr.azmobile.feature.album.presentation.base.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class BaseDataSourceFactory<T>(
    private val compositeDisposable: CompositeDisposable,
    private val getList: (page: Int, itemsPerPage: Int) -> Single<List<T>>
) : DataSource.Factory<Int, T>() {

    internal val dataSourceLiveData = MutableLiveData<BaseDataSource<T>>()

    override fun create(): DataSource<Int, T> {
        val dataSource = BaseDataSource(getList, compositeDisposable)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}