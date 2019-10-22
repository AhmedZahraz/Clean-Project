package fr.azmobile.feature.album.presentation.application

import android.app.Application
import fr.azmobile.feature.album.presentation.dagger.AppComponent
import fr.azmobile.feature.album.presentation.dagger.AppModule
import fr.azmobile.feature.album.presentation.dagger.DaggerAppComponent

/**
 * Created by ZAHRAZ Ahmed on 2019-10-21.
 * Copyright (c) 2019 JohnPaul. All rights reserved.
 */

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }
    private fun initDagger(app: MyApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}