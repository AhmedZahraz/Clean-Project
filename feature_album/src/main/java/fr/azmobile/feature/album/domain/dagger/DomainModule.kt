package fr.azmobile.feature.album.domain.dagger

import dagger.Module
import dagger.Provides
import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepository
import fr.azmobile.feature.album.data.remote.repository.AlbumRemoteRepository
import fr.azmobile.feature.album.domain.repository.AlbumRepositoryImpl
import fr.azmobile.feature.album.domain.repository.AlbumRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    @Inject
    internal fun provideAlbumRepository(
        albumRemoteRepository: AlbumRemoteRepository,
        albumCacheRepository: AlbumCacheRepository
    ): AlbumRepository {
        return AlbumRepositoryImpl(
            albumRemoteRepository,
            albumCacheRepository
        )
    }
}