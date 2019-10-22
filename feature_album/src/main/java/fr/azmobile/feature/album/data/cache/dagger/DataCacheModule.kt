package fr.azmobile.feature.album.data.cache.dagger

import android.content.Context
import dagger.Provides
import javax.inject.Singleton
import androidx.room.Room
import dagger.Module
import fr.azmobile.feature.album.data.cache.db.AlbumDao
import fr.azmobile.feature.album.data.cache.db.AppDatabase
import fr.azmobile.feature.album.data.cache.db.ItemDao
import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepository
import fr.azmobile.feature.album.data.cache.repository.AlbumCacheRepositoryImpl


/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

@Module
class DataCacheModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "leboncoin-database-name"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.albumDao()
    }

    @Singleton
    @Provides
    fun provideItemDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    internal fun provideAlbumRemoteRepository(albumDao: AlbumDao, itemDao: ItemDao): AlbumCacheRepository {
        return AlbumCacheRepositoryImpl(albumDao, itemDao)
    }
}