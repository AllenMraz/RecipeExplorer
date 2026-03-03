package com.example.recipeexplorer

import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.recipeexplorer.data.Recipe


@Composable
fun RecipeDetailsScreen(
    recipeUiState: RecipeUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
){
    BackHandler() {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding(),
            ),
            modifier = Modifier
                .testTag(stringResource(R.string.details_screen))
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            item {
                if (isFullScreen) {
                    RecipeDetailsScreenTopBar(
                        onBackPressed,
                        recipeUiState,
                        Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom),
                                top = dimensionResource((R.dimen.topbar_padding_vertical))
                            )
                    )
                }

                RecipeDetailsCard(
                    recipe = recipeUiState.currentSelectedRecipe,
                    isFullScreen = isFullScreen,
                    modifier = if (isFullScreen) {
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    } else {
                        Modifier
                    }
                )
            }
        }
    }
}

@Composable
private fun RecipeDetailsScreenTopBar(
    onBackPressed: () -> Unit,
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier
){
    Row(
       modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(recipeUiState.currentSelectedRecipe.name),

        )
    }

}

@Composable
private fun RecipeDetailsCard(
    recipe: Recipe,
    modifier: Modifier,
    isFullScreen: Boolean = false
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.detail_card_inner_padding))
    ) {
        DetailsScreenHeader(
            recipe,
            Modifier.fillMaxSize()
        )
        if(isFullScreen){
            Spacer(modifier = modifier.height(dimensionResource(R.dimen.detail_content_padding_top)))
        } else {
            Text(
                text = stringResource(recipe.name),
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.detail_content_padding_top),
                    bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                ),
            )
        }
        Text(
            text = stringResource((recipe.description)),

        )
    }
}

@Composable
private fun DetailsScreenHeader(recipe: Recipe, modifier: Modifier = Modifier)
{
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.recipe_header_content_padding_horizontal),
                    vertical = dimensionResource((R.dimen.recipe_header_content_padding_vertical))

                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(recipe.name)
            )
        }
    }
}



