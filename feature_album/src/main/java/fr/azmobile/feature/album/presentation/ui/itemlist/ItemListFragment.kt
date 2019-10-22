package fr.azmobile.feature.album.presentation.ui.itemlist

import fr.azmobile.feature.album.domain.model.ItemDomainModel
import fr.azmobile.feature.album.presentation.application.MyApplication
import fr.azmobile.feature.album.presentation.base.adapter.BaseListAdapter
import fr.azmobile.feature.album.presentation.base.fragment.BaseListFragment
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModelImp
import fr.azmobile.feature.album.presentation.ui.itemlist.adapter.ItemListAdapter

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class ItemListFragment : BaseListFragment<ItemDomainModel>() {

    override fun inject() {
        (activity?.application as? MyApplication)?.appComponent?.inject(this)
    }

    override fun viewModelClass(): Class<out BaseListViewModelImp<ItemDomainModel>> {
        return ItemListViewModelImp::class.java
    }

    override fun adapter(retry: () -> Unit): BaseListAdapter<ItemDomainModel> {
        return ItemListAdapter(retry)
    }

    companion object {
        fun newInstance() = ItemListFragment()
    }
}