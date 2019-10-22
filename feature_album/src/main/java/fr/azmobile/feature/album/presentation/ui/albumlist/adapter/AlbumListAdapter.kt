package fr.azmobile.feature.album.presentation.ui.albumlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import fr.azmobile.feature.album.R
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.presentation.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.fragment_album_list_item.view.*

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class AlbumListAdapter(retry: () -> Unit): BaseListAdapter<AlbumDomainModel>(retry, AlbumDiffCallback) {

    override fun getNewViewHolder(parent: ViewGroup): ViewHolder<AlbumDomainModel> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_album_list_item, parent, false)
        return AlbumViewHolder(view)
    }

    companion object {
        val AlbumDiffCallback = object : DiffUtil.ItemCallback<AlbumDomainModel>() {
            override fun areItemsTheSame(oldItem: AlbumDomainModel, newItem: AlbumDomainModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AlbumDomainModel, newItem: AlbumDomainModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class AlbumViewHolder(view: View): ViewHolder<AlbumDomainModel>(view) {

        override fun bind(
            model: AlbumDomainModel?,
            onDebouncedClickListener: ((album: AlbumDomainModel) -> Unit)?
        ) {
            itemView.setOnClickListener {
                if (model != null) {
                    onDebouncedClickListener?.invoke(model)
                }
            }
            itemView.titleTextView.text = itemView.context.resources.getString(R.string.album_id, model?.id)
        }

    }
}