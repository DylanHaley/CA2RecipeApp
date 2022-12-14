package org.setu.recipeApp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.setu.recipeApp.R
import org.setu.recipeApp.databinding.ActivityMenuBinding
import org.setu.recipeApp.main.MainApp

class MenuActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        binding.btnRecipes.setOnClickListener{
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}