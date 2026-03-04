package com.example.recipeexplorer.data.local

import com.example.recipeexplorer.R
import com.example.recipeexplorer.data.Recipe

object LocalRecipes{

    val allRecipes = listOf( // list of recipes

        Recipe(
             id = 1,
            name = R.string.recipe_1_name,
            description = R.string.recipe_1_details
        ),

        Recipe(
            id = 2,
            name = R.string.recipe_2_name,
            description = R.string.recipe_2_details
        ),

        Recipe(
            id = 3,
            name = R.string.recipe_3_name,
            description = R.string.recipe_3_details
        )
    )
    fun get(id: Int): Recipe? {
        return allRecipes.firstOrNull(){it.id == id}
    }

    val emptyRecipe = Recipe(
        id = 0,
        name = R.string.empty_recipe,
        description = R.string.empty_recipe
    )
}