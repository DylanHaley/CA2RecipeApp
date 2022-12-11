package org.setu.recipeApp.main

import android.app.Application
import org.setu.recipeApp.models.RecipeJSONStore
import org.setu.recipeApp.models.RecipeStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var recipes: RecipeStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        recipes = RecipeJSONStore(applicationContext)
        i("Recipe started")
    }
}