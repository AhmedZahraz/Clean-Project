package fr.azmobile.feature.album.presentation.ui.itemlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import fr.azmobile.feature.album.R
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import fr.azmobile.feature.album.presentation.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.fragment_item_list_item.view.*

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class ItemListAdapter(retry: () -> Unit) :
    BaseListAdapter<ItemDomainModel>(retry, AlbumDiffCallback) {

    override fun getNewViewHolder(parent: ViewGroup): ViewHolder<ItemDomainModel> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_list_item, parent, false)
        return ItemViewHolder(view)
    }

    companion object {
        val AlbumDiffCallback = object : DiffUtil.ItemCallback<ItemDomainModel>() {
            override fun areItemsTheSame(
                oldItem: ItemDomainModel,
                newItem: ItemDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ItemDomainModel,
                newItem: ItemDomainModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ItemViewHolder(view: View) : ViewHolder<ItemDomainModel>(view) {

        override fun bind(
            model: ItemDomainModel?,
            onDebouncedClickListener: ((album: ItemDomainModel) -> Unit)?
        ) {
            itemView.setOnClickListener {
                if (model != null) {
                    onDebouncedClickListener?.invoke(model)
                }
            }
            Picasso.get().load(model?.thumbnailURL)
                .error(R.drawable.placeholder_leboncoin)
                .placeholder(R.drawable.placeholder_leboncoin)
                .into(itemView.imageView)
            itemView.titleTextView.text = model?.title
        }

    }
}