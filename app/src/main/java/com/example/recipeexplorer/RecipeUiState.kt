package com.example.recipeexplorer

import com.example.recipeexplorer.data.Recipe

data class RecipeUiState(
    val recipeList: List<Recipe>,
    val isShowingHomepage: Boolean = true
){
    val currentRecipe: List<Recipe> by lazy { recipeList }
}
