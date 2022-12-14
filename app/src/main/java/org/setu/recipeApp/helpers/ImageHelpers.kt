package org.setu.recipeApp.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import org.setu.recipeApp.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {

    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.button_recipe_image.toString())
    intentLauncher.launch(chooseFile)
}