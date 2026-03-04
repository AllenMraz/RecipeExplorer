package com.example.recipeexplorer.data

import androidx.annotation.StringRes

// data class for recipes
data class Recipe(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)
