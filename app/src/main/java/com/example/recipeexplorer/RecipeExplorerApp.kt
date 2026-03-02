package com.example.recipeexplorer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeexplorer.ui.utils.RecipeNavigationType

@Composable
fun RecipeExplorerApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
){
    val navigationType: RecipeNavigationType
    when (windowSize){
        WindowWidthSizeClass.Compact -> {
            navigationType = RecipeNavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = RecipeNavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = RecipeNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            navigationType = RecipeNavigationType.BOTTOM_NAVIGATION
        }
    }
}