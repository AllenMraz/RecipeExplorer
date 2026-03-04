package com.example.recipeexplorer

import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.data.local.LocalRecipes

data class RecipeUiState(
    val recipeList: List<Recipe> = emptyList(), // a list of recipes
    val currentSelectedRecipe: Recipe = LocalRecipes.emptyRecipe, // the selected recipe
    val isShowingHomepage: Boolean = true // indicater if it is on the home page or not
    ){
    val currentRecipes: List<Recipe> by lazy {recipeList} //the list of current recipes
}
