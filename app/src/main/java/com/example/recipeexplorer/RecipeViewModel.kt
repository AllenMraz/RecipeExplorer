package com.example.recipeexplorer

import androidx.lifecycle.ViewModel
import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.data.local.LocalRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// The viewModel for recipes
class RecipeViewModel : ViewModel(){
    // Mutable State Flow that repersents the recipe list
    private val _uiState = MutableStateFlow(RecipeUiState())
    // a read only version if _uiState
    val uiState: StateFlow<RecipeUiState> = _uiState

    init{
        initializeUIState()
    }
    //initializes and puts all recipes into a list
    private fun initializeUIState(){
        val recipes: List<Recipe> = LocalRecipes.allRecipes

        _uiState.value =
            RecipeUiState(
                recipes
            )
    }
    // grabs recipe for a detailed view
    fun updateDetailsScreenStates(recipe: Recipe) {
        _uiState.update{
            it.copy(
                currentSelectedRecipe = recipe,
                isShowingHomepage = false
            )
        }
    }
    // resets the home screen
    fun resetHomeScreensStates() {
        _uiState.update {
            it.copy(
                currentSelectedRecipe = it.currentSelectedRecipe,
                isShowingHomepage = true
            )
        }
    }
}