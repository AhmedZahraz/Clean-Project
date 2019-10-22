package fr.azmobile.feature.album.presentation.ui.albumlist

import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModelImp
import io.reactivex.Single

internal class AlbumListViewModelImp(getList: (page: Int, itemsPerPage: Int) -> Single<List<AlbumDomainModel>>) :
    BaseListViewModelImp<AlbumDomainModel>(getList)
