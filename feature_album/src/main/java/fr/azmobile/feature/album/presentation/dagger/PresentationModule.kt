package fr.azmobile.feature.album.presentation.dagger

import dagger.Module
import dagger.Provides
import fr.azmobile.feature.album.domain.repository.AlbumRepository
import fr.azmobile.feature.album.presentation.base.factory.ViewModelFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    @Inject
    internal fun provideViewModelFactory(albumRepository: AlbumRepository): ViewModelFactory {
        return ViewModelFactory(albumRepository)
    }
}