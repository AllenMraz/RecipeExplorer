package com.example.recipeexplorer


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipeexplorer.data.Recipe

// method that displays the detailed information of a recipe
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
                    RecipeDetailsScreenTopBar( // a call to render the topbar when applicable
                        onBackPressed = onBackPressed,
                        recipeUiState,
                        Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom),
                                top = dimensionResource((R.dimen.topbar_padding_vertical))
                            )
                    )
                }

                RecipeDetailsCard( // calls recipeDetailsCard to get the information of the recipe
                    onBackPressed = onBackPressed,
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
private fun RecipeDetailsScreenTopBar( // the top bar for the deatail view
    onBackPressed: () -> Unit,
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier
){

        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 16.dp,
                    bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                    .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.navigation_back)
                )
            }
            Text(
                text = stringResource(recipeUiState.currentSelectedRecipe.name),

                )
    }

}

@Composable
private fun RecipeDetailsCard( // method that gets the information for the detail
    onBackPressed: () -> Unit,
    recipe: Recipe,
    modifier: Modifier,
    isFullScreen: Boolean = false
){

        if(isFullScreen){
            Spacer(modifier = modifier.height(dimensionResource(R.dimen.detail_content_padding_top)))
        } else {
            Text(
                text = stringResource(recipe.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.detail_content_padding_top),
                    bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                ),
            )
        }
        Text(
            text = stringResource(recipe.description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp)
        )

}




