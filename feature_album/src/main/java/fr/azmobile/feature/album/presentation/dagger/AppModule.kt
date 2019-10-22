package fr.azmobile.feature.album.presentation.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ahmed on 17/04/2018.
 */

// The @Module annotation tells Dagger that the AppModule class will provide dependencies for a part of the application
@Module
class AppModule(private val app: Application) {

    // The @Provides annotation tells Dagger that the method provides a certain type of dependency, in this case, a Context object.
    // The method names for the providers, such as provideContext(), are not important and can be named anything you like. Dagger only looks at the return type. Using provide as a prefix is a common convention.
    @Provides
    // The @Singleton annotation is not part of the Dagger AP.
    // It tells Dagger that there should only be a single instance of that dependency. So when generating the code Dagger will handle all the logic for you, and you won’t write all the boilerplate code to check if another instance of the object is already available.
    @Singleton
    fun provideContext(): Context = app

}