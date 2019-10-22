package fr.azmobile.feature.album.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import fr.azmobile.feature.album.R
import fr.azmobile.feature.album.domain.model.AlbumDomainModel
import fr.azmobile.feature.album.domain.model.ItemDomainModel
import fr.azmobile.feature.album.presentation.ui.albumlist.AlbumListFragment
import fr.azmobile.feature.album.presentation.base.fragment.BaseListFragment
import fr.azmobile.feature.album.presentation.ui.itemdetail.ItemDetailFragment
import fr.azmobile.feature.album.presentation.ui.itemlist.ItemListFragment

class MainActivity : AppCompatActivity(), BaseListFragment.OnHeadlineSelectedListener<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            startFragment(AlbumListFragment.newInstance())
        }
    }

    override fun onItemSelected(item: Any) {
        if (item is AlbumDomainModel) {
            startFragment(ItemListFragment.newInstance())
        } else if (item is ItemDomainModel) {
            startFragment(ItemDetailFragment.newInstance(item))
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun startFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }
}
