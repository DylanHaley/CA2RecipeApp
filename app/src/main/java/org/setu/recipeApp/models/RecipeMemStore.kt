package org.setu.recipeApp.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RecipeMemStore : RecipeStore {

    val recipes = ArrayList<RecipeModel>()

    override fun findAll(): List<RecipeModel> {
        return recipes
    }

    override fun create(recipe: RecipeModel) {
        recipe.id = getId()
        recipes.add(recipe)
        logAll()
    }

    override fun update(recipe: RecipeModel) {
        val foundrecipe: RecipeModel? = recipes.find { p -> p.id == recipe.id }
        if (foundrecipe != null) {
            foundrecipe.title = recipe.title
            foundrecipe.description = recipe.description
            foundrecipe.image = recipe.image
            foundrecipe.lat = recipe.lat
            foundrecipe.lng = recipe.lng
            foundrecipe.zoom = recipe.zoom
            logAll()
        }
    }

    private fun logAll() {
        recipes.forEach { i("$it") }
    }
}