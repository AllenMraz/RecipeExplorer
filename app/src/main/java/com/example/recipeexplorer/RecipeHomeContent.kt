package com.example.recipeexplorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.recipeexplorer.data.Recipe

@Composable
fun RecipeListOnlyContent(
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier = Modifier
){
    val recipes = recipeUiState.currentRecipes
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.recipe_list_item_vertical_spacing)
        )
    ) {

        items(recipes, key ={recipe -> recipe.id}) { recipe ->
            RecipeListItem(
                recipe = recipe,
                selected = false,
                onCardClick = {
                    onRecipeCardPressed(recipe)
                }
            )
        }

    }
}

@Composable
fun RecipeListItem(
    recipe: Recipe,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier= Modifier.fillMaxSize()
            .padding(dimensionResource((R.dimen.recipe_list_item_inner_padding)))
    ){
        Text(
            text = stringResource(recipe.name),
        )
        Text(
            text = stringResource((recipe.description))
        )
    }
}