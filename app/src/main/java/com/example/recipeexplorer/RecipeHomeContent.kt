package com.example.recipeexplorer

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
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

@SuppressLint("ContextCastToActivity")
@Composable
fun RecipeListAndDetailContent(
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier = Modifier
){
    val recipes = recipeUiState.currentRecipes
    Row(
        modifier = modifier,
        horizontalArrangement =  Arrangement.SpaceEvenly
    ){
        LazyColumn(
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.recipe_list_only_horizontal_padding)),
            verticalArrangement = Arrangement.spacedBy((
                    dimensionResource(R.dimen.recipe_list_item_vertical_spacing)
                    ))
        ) {
            items(recipes, key ={recipes -> recipes.id}){recipe ->
                RecipeListItem(
                    recipe = recipe,
                    selected = recipeUiState.currentSelectedRecipe.id == recipe.id,
                    onCardClick = {
                        onRecipeCardPressed(recipe)
                    }
                )
            }
        }
        val activity = LocalContext.current as Activity
        RecipeDetailsScreen(
            recipeUiState = recipeUiState,
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(R.dimen.recipe_list_only_horizontal_padding)),
            onBackPressed = {activity.finish()}
        )
    }

}

@Composable
fun RecipeListItem(
    recipe: Recipe,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected){
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(dimensionResource((R.dimen.recipe_list_item_inner_padding)))
        ) {
            Text(
                text = stringResource(recipe.name),
            )
            Text(
                text = stringResource((recipe.description))
            )
        }
    }
}