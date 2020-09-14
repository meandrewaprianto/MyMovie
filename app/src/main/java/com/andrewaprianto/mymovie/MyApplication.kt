package com.andrewaprianto.mymovie

import android.app.Application
import com.andrewaprianto.mymovie.core.di.databaseModule
import com.andrewaprianto.mymovie.core.di.networkModule
import com.andrewaprianto.mymovie.core.di.repositoryModule
import com.andrewaprianto.mymovie.di.useCaseModule
import com.andrewaprianto.mymovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }


}