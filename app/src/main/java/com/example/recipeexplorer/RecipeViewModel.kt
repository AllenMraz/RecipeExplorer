package com.example.recipeexplorer

import androidx.lifecycle.ViewModel
import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.data.local.LocalRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState

    init{
        initializeUIState()
    }
    private fun initializeUIState(){
        val recipes: List<Recipe> = LocalRecipes.allRecipes

        _uiState.value =
            RecipeUiState(
                recipes
            )
    }

    fun updateDetailsScreenStates(recipe: Recipe) {
        _uiState.update{
            it.copy(
                currentSelectedRecipe = recipe,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreensStates() {
        _uiState.update {
            it.copy(
                currentSelectedRecipe = it.currentSelectedRecipe,
                isShowingHomepage = true
            )
        }
    }
}