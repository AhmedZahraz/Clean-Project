package fr.azmobile.feature.album.presentation.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.azmobile.feature.album.R
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.presentation.base.adapter.BaseListAdapter
import fr.azmobile.feature.album.presentation.base.factory.ViewModelFactory
import fr.azmobile.feature.album.presentation.base.model.State
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModel
import fr.azmobile.feature.album.presentation.base.viewmodel.BaseListViewModelImp
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

abstract class BaseListFragment<T> : Fragment() {

    var mViewModelFactory: ViewModelFactory? = null
        @Inject set

    private lateinit var viewModel: BaseListViewModel<T>

    private lateinit var albumAdapter: BaseListAdapter<T>

    private var callback: OnHeadlineSelectedListener<T>? = null


    abstract fun inject()
    abstract fun viewModelClass(): Class<out BaseListViewModelImp<T>>
    abstract fun adapter(retry: () -> Unit): BaseListAdapter<T>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? OnHeadlineSelectedListener<T>)?.let {
            setOnHeadlineSelectedListener(it)
        }
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()

        viewModel =
            ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass())

        initAdapter()
        initState()
    }

    private fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener<T>) {
        this.callback = callback
    }

    private fun initAdapter() {
        albumAdapter = adapter { viewModel.retry() }
        albumAdapter.setOnDebouncedClickListener {
            if (it is AlbumDomainModel) {
                mViewModelFactory?.setAlbumDomainModel(it)
            }
            callback?.onItemSelected(it)
        }
        recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
        viewModel.getAlbumLiveData().observe(this, Observer {
            albumAdapter.submitList(it)
        })
    }

    private fun initState() {
        errorTextView.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progressBar.visibility =
                if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            errorTextView.visibility =
                if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                albumAdapter.setState(state ?: State.DONE)
            }
        })
    }

    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    interface OnHeadlineSelectedListener<T> {
        fun onItemSelected(item: T)
    }

}
