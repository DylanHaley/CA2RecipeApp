package org.setu.recipeApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.setu.recipeApp.databinding.ActivityMainBinding
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
        val binding = ActivityMainBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding: ActivityMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel, listener: RecipeListener) {
            binding.recipeTitle.setText(recipe.title) //recipe.title
            binding.description.setText(recipe.description) //recipe.description
            Picasso.get().load(recipe.image).resize(200,200).into(binding.recipeImage)
//            Glide.with(binding.root)
//                .load(recipe.image)
//                .override(200,200)
//                .into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onRecipeClick(recipe) }
        }
    }
}
