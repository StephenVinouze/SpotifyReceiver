package com.wopata.spotifyreceiver

import android.app.Application
import timber.log.Timber

/**
 * Created by stephenvinouze on 22/02/2017.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}