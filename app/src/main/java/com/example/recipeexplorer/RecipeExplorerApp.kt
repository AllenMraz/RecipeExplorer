package com.example.recipeexplorer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.ui.utils.RecipeContentType
import com.example.recipeexplorer.ui.utils.RecipeNavigationType

@Composable
fun RecipeExplorerApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
){
    val navigationType: RecipeNavigationType
    val contentType: RecipeContentType
    val viewModel: RecipeViewModel = viewModel()
    val recipeUiState = viewModel.uiState.collectAsState().value
    when (windowSize){
        WindowWidthSizeClass.Compact -> {
            navigationType = RecipeNavigationType.BOTTOM_NAVIGATION
            contentType = RecipeContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = RecipeNavigationType.NAVIGATION_RAIL
            contentType= RecipeContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = RecipeNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = RecipeContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = RecipeNavigationType.BOTTOM_NAVIGATION
            contentType = RecipeContentType.LIST_ONLY
        }
    }
    RecipeHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        recipeUiState = recipeUiState,
        onRecipeCardPressed = {recipe: Recipe ->
            viewModel.updateDetailsScreenStates(recipe = recipe)
        },
        onDetailScreenBackPressed = {viewModel.resetHomeScreensStates()},
        modifier = modifier
    )

}