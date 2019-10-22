package fr.azmobile.feature.album.presentation.dagger

import dagger.Component
import fr.azmobile.feature.album.domain.dagger.DomainModule
import fr.azmobile.demo.presentation.dagger.PresentationModule
import fr.azmobile.feature.album.data.cache.dagger.DataCacheModule
import fr.azmobile.feature.album.data.remote.dagger.DataRemoteModule
import fr.azmobile.feature.album.presentation.ui.albumlist.AlbumListFragment
import fr.azmobile.feature.album.presentation.ui.itemlist.ItemListFragment
import javax.inject.Singleton

/**
 * Created by ahmed on 17/04/2018.
 */
@Singleton
@Component(modules = [(AppModule::class), (PresentationModule::class), (DomainModule::class), (DataCacheModule::class), (DataRemoteModule::class)])
// The component is used to connect objects to their dependencies, typically by use of overridden inject() methods
// Here, you’ve told Dagger that AppComponent is a singleton component interface for the app.
interface AppComponent {
    fun inject(target: AlbumListFragment)
    fun inject(target: ItemListFragment)
}