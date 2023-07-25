package org.setu.recipeApp.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel (
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var image: Uri = Uri.EMPTY,
    var rating: Double = 0.0,
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 0f,
    var isFavourite: Boolean = false // Added field for favorites
) : Parcelable


@Parcelize
data class Location (var lat: Double = 0.0,
                     var lng: Double = 0.0,
                     var zoom: Float = 0f) : Parcelable