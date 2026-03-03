package com.example.recipeexplorer

import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.data.local.LocalRecipes

data class RecipeUiState(
    val recipeList: List<Recipe> = emptyList(),
    val currentSelectedRecipe: Recipe = LocalRecipes.emptyRecipe,
    val isShowingHomepage: Boolean = true
    ){
    val currentRecipes: List<Recipe> by lazy {recipeList}
}
