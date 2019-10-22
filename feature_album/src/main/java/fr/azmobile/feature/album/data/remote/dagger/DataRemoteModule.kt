package fr.azmobile.feature.album.data.remote.dagger

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import fr.azmobile.feature.album.BuildConfig
import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepository
import fr.azmobile.feature.album.data.remote.LeBonCoinAPI
import fr.azmobile.feature.album.data.remote.moshi.AlbumListAdapter
import fr.azmobile.feature.album.data.remote.repository.AlbumRemoteRepository
import fr.azmobile.feature.album.data.remote.repository.AlbumRemoteRepositoryImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by ahmed on 17/04/2018.
 */
@Module
class DataRemoteModule {

    private fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    internal fun provideLeBonCoinAPI(albumCacheRepository: AlbumCacheRepository): LeBonCoinAPI {
        val retrofit = retrofitBuilder()
            .baseUrl(BuildConfig.STATIC_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(AlbumListAdapter(albumCacheRepository)).build()
                )
            )
            .build()

        return retrofit.create(LeBonCoinAPI::class.java)
    }

    @Provides
    @Singleton
    internal fun provideAlbumRemoteRepository(leBonCoinAPI: LeBonCoinAPI): AlbumRemoteRepository {
        return AlbumRemoteRepositoryImpl(leBonCoinAPI)
    }

}