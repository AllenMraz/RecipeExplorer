package com.example.recipeexplorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.ui.utils.RecipeContentType
import com.example.recipeexplorer.ui.utils.RecipeNavigationType

@Composable
fun RecipeHomeScreen( // method that calls the display depending on screen size
    navigationType: RecipeNavigationType,
    contentType: RecipeContentType,
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    if (navigationType == RecipeNavigationType.PERMANENT_NAVIGATION_DRAWER && recipeUiState.isShowingHomepage){
        val navigationDrawerContentDeprecated = stringResource(R.string.navigation_drawer)
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width)),
                    drawerContentColor = MaterialTheme.colorScheme.inverseOnSurface) {

                }
            },
            modifier = Modifier.testTag(navigationDrawerContentDeprecated)
        ) {
            RecipeAppContent( // calls an method that gets the display
                navigationType = navigationType,
                contentType = contentType,
                recipeUiState = recipeUiState,
                onRecipeCardPressed = onRecipeCardPressed,
                modifier = modifier
            )
        }
    } else {
        if (recipeUiState.isShowingHomepage){
            RecipeAppContent( // calls an method that gets the display
                navigationType = navigationType,
                contentType = contentType,
                recipeUiState = recipeUiState,
                onRecipeCardPressed =onRecipeCardPressed,
                modifier = modifier
            )
        } else {
            RecipeDetailsScreen( // displays the detail screen
                recipeUiState = recipeUiState,
                onBackPressed = onDetailScreenBackPressed,
                modifier = modifier,
                isFullScreen = true
            )
        }
    }

}

@Composable
private fun RecipeAppContent( // method that calls the list display acording to screen size
    navigationType:  RecipeNavigationType,
    contentType: RecipeContentType,
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier){

        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.inverseOnSurface)
        ){
            if (contentType == RecipeContentType.LIST_AND_DETAIL){
                RecipeListAndDetailContent( // displays both list and detail content
                    recipeUiState = recipeUiState,
                    onRecipeCardPressed = onRecipeCardPressed,
                    modifier = Modifier
                        .statusBarsPadding()
                        .weight(1f)
                )
            }else {
                RecipeListOnlyContent( // only displays list
                    recipeUiState = recipeUiState,
                    onRecipeCardPressed = onRecipeCardPressed,
                    modifier = Modifier.weight(1f)
                        .padding(horizontal = dimensionResource(R.dimen.recipe_list_only_horizontal_padding))
                )
            }
        }
    }
}





