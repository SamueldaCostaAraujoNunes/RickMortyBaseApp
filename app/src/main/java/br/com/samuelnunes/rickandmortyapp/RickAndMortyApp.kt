package br.com.samuelnunes.rickandmortyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 08/07/2021 at 13:26
 */

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}