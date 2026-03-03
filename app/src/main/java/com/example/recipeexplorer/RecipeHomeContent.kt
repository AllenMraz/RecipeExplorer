package com.example.recipeexplorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.recipeexplorer.data.Recipe

@Composable
fun RecipeListOnlyContent(
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier = Modifier
){
    val recipes = recipeUiState
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.recipe_list_item_vertical_spacing)
        )
    ) {
        {

        }
    }
}