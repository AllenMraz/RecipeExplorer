package com.example.recipeexplorer

import androidx.lifecycle.ViewModel
import com.example.recipeexplorer.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(RecipeUiState(MutableList(3){i -> Recipe(i, name = i, description = i)}))
    val uiState: StateFlow<RecipeUiState> = _uiState

}