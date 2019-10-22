package fr.azmobile.feature.album.presentation.ui.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.azmobile.feature.album.R
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import kotlinx.android.synthetic.main.fragment_item_detail.*

private const val ARG_PARAM1 = "param1"

class ItemDetailFragment : Fragment() {

    private var itemDomainModel: ItemDomainModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemDomainModel = it.getParcelable<ItemDomainModel>(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemDomainModel?.apply {
            Picasso.get().load(imageURL)
                .into(imageView);
            titleTextView.text = title
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ItemDomainModel) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }
}
