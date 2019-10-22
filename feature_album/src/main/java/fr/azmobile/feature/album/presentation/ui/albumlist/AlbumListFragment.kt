package fr.azmobile.feature.album.presentation.ui.albumlist

import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.presentation.ui.albumlist.adapter.AlbumListAdapter
import fr.azmobile.feature.album.presentation.application.MyApplication
import fr.azmobile.feature.album.presentation.base.adapter.BaseListAdapter
import fr.azmobile.feature.album.presentation.base.fragment.BaseListFragment
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModelImp

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class AlbumListFragment : BaseListFragment<AlbumDomainModel>() {

    override fun inject() {
        (activity?.application as? MyApplication)?.appComponent?.inject(this)
    }

    override fun viewModelClass(): Class<out BaseListViewModelImp<AlbumDomainModel>> {
        return AlbumListViewModelImp::class.java
    }

    override fun adapter(retry: () -> Unit): BaseListAdapter<AlbumDomainModel> {
        return AlbumListAdapter(retry)
    }

    companion object {
        fun newInstance() = AlbumListFragment()
    }
}