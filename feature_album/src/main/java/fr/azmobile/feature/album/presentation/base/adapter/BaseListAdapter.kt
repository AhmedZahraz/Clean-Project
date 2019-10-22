package fr.azmobile.feature.album.presentation.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.azmobile.feature.album.presentation.base.model.State
import fr.azmobile.feature.album.presentation.base.paging.ListFooterViewHolder

/**
 * Created by ZAHRAZ Ahmed on 2019-10-22.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

abstract class BaseListAdapter<T>(
    private val retry: () -> Unit,
    diffUtil: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(diffUtil) {

    private val dataViewType = 1
    private val footerViewType = 2

    private var state = State.LOADING

    private var onDebouncedClickListener: ((item: T) -> Unit)? = null

    fun setOnDebouncedClickListener(listener: (item: T) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    abstract fun getNewViewHolder(parent: ViewGroup): ViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == dataViewType) {
            getNewViewHolder(parent)
        } else ListFooterViewHolder.create(
            retry,
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == dataViewType)
            (holder as ViewHolder<T>).bind(getItem(position), onDebouncedClickListener)
        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) dataViewType else footerViewType
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    abstract class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(
            model: T?,
            onDebouncedClickListener: ((album: T) -> Unit)?
        )
    }
}