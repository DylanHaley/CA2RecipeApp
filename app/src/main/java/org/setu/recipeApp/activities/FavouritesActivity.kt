package org.setu.recipeApp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.setu.recipeApp.R
import org.setu.recipeApp.adapters.RecipeAdapter
import org.setu.recipeApp.adapters.RecipeListener
import org.setu.recipeApp.databinding.ActivityFavouritesBinding
import org.setu.recipeApp.main.MainApp
import org.setu.recipeApp.models.RecipeModel

class FavouritesActivity : AppCompatActivity(), RecipeListener {

    private lateinit var binding: ActivityFavouritesBinding
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.favouritesRecyclerView.layoutManager = layoutManager

        showFavouriteRecipes()
    }

    override fun onRecipeClick(recipe: RecipeModel) {
    }

    private fun showFavouriteRecipes() {
        val favouriteRecipes = app.recipes.findAll().filter { it.isFavourite }
        binding.favouritesRecyclerView.adapter = RecipeAdapter(favouriteRecipes, this)
    }
}
