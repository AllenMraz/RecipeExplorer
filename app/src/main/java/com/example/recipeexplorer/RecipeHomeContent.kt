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
import androidx.compose.ui.text.style.TextOverflow
import com.example.recipeexplorer.data.Recipe

@Composable
fun RecipeListOnlyContent( // shows only the recipe list
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier = Modifier
){
    val recipes = recipeUiState.currentRecipes
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.recipe_list_item_vertical_spacing)
        )
    ) {

        items(recipes, key ={recipe -> recipe.id}) { recipe ->
            RecipeListItem( // calls a method that gets a list of recipes
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
fun RecipeListAndDetailContent( // shows both the recipe list and detail view of the chosen recipe
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier = Modifier
){
    val recipes = recipeUiState.currentRecipes
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement =  Arrangement.SpaceEvenly
    ){
        LazyColumn(
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.recipe_list_only_horizontal_padding)),
            verticalArrangement = Arrangement.spacedBy((
                    dimensionResource(R.dimen.recipe_list_item_vertical_spacing)
                    ))
        ) {
            items(recipes, key ={recipes -> recipes.id}){recipe ->
                RecipeListItem(// calls a method that gets a list of recipes
                    recipe = recipe,
                    selected = recipeUiState.currentSelectedRecipe.id == recipe.id,
                    onCardClick = {
                        onRecipeCardPressed(recipe)
                    }
                )
            }
        }
        val activity = LocalContext.current as Activity
        RecipeDetailsScreen( // calls a method that gets the detailed view
            recipeUiState = recipeUiState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = dimensionResource(R.dimen.recipe_list_only_horizontal_padding)),
            onBackPressed = {activity.finish()}
        )
    }

}

@Composable
fun RecipeListItem( // method that gets a list of recipeys and puts them into cards
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
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.recipe_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.recipe_list_item_subject_body_spacing)
                )
            )
            Text(
                text = stringResource((recipe.description)),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}