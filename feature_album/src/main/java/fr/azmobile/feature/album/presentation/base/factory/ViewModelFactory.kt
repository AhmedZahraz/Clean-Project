package fr.azmobile.feature.album.presentation.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import fr.azmobile.feature.album.domain.repository.AlbumRepository
import fr.azmobile.feature.album.presentation.ui.albumlist.AlbumListViewModelImp
import fr.azmobile.feature.album.presentation.ui.itemlist.ItemListViewModelImp
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class ViewModelFactory(@set:Inject var albumRepository: AlbumRepository) : ViewModelProvider.Factory {

    private var albumDomainModel: AlbumDomainModel? = null

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumListViewModelImp::class.java)) {
            return AlbumListViewModelImp(albumRepository::getAlbumList) as T
        } else if (modelClass.isAssignableFrom(ItemListViewModelImp::class.java)) {
            return ItemListViewModelImp(::getList) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }

    private fun getList(page: Int, itemsPerPage: Int): Single<List<ItemDomainModel>> {
        albumDomainModel?.id?.let {
            return albumRepository.getItemList(it, page, itemsPerPage)
        }
        throw IllegalArgumentException("Album ID Not Found")
    }

    fun setAlbumDomainModel(albumDomainModel: AlbumDomainModel?) {
        this.albumDomainModel = albumDomainModel
    }
}