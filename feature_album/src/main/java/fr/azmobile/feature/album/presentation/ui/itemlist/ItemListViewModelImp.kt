package fr.azmobile.feature.album.presentation.ui.itemlist

import fr.azmobile.feature.album.domain.model.ItemDomainModel
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModelImp
import io.reactivex.Single

internal class ItemListViewModelImp(getList: (page: Int, itemsPerPage: Int) -> Single<List<ItemDomainModel>>) :
    BaseListViewModelImp<ItemDomainModel>(getList)