package org.setu.recipeApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.setu.recipeApp.R
import org.setu.recipeApp.databinding.CardRecipeBinding
import org.setu.recipeApp.models.RecipeModel

interface RecipeListener {
    fun onRecipeClick(recipe: RecipeModel)
}

class RecipeAdapter constructor(
    private var recipes: List<RecipeModel>,
    private val listener: RecipeListener
) :
    RecyclerView.Adapter<RecipeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding: CardRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel, listener: RecipeListener) {
            binding.recipeName.text = recipe.name
            binding.recipeDescription.text = recipe.description
            Picasso.get().load(recipe.image).resize(200, 200).into(binding.recipeImage)

            updateFavoriteButton(recipe)

            binding.favoriteButton.setOnClickListener {
                recipe.isFavourite = !recipe.isFavourite
                updateFavoriteButton(recipe)
            }

            binding.root.setOnClickListener { listener.onRecipeClick(recipe) }
        }

        private fun updateFavoriteButton(recipe: RecipeModel) {
            val favoriteDrawableRes =
                if (recipe.isFavourite) R.drawable.ic_favourite else R.drawable.ic_add_favourites
            binding.favoriteButton.setImageResource(favoriteDrawableRes)
        }
    }
}
