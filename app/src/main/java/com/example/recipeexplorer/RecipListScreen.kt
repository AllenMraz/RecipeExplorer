package com.example.recipeexplorer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.recipeexplorer.data.Recipe
import com.example.recipeexplorer.ui.utils.RecipeContentType
import com.example.recipeexplorer.ui.utils.RecipeNavigationType

@Composable
fun RecipeHomeScreen(
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
            RecipeAppContent(
                navigationType = navigationType,
                recipeUiState = recipeUiState,
                onRecipeCardPressed = onRecipeCardPressed,
                modifier = modifier
            )
        }
    } else {
        if (recipeUiState.isShowingHomepage){
            RecipeAppContent(
                navigationType = navigationType,
                recipeUiState = recipeUiState,
                onRecipeCardPressed =onRecipeCardPressed,
                modifier = modifier
            )
        }
    }

}

@Composable
private fun RecipeAppContent(
    navigationType:  RecipeNavigationType,
    recipeUiState: RecipeUiState,
    onRecipeCardPressed: (Recipe) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier){

        Row(modifier = modifier.fillMaxSize()){
            AnimatedVisibility(visible = navigationType == RecipeNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDeprecated = stringResource(R.string.navigation_rail)

            }
        }
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.inverseOnSurface)
        ){
         RecipeListOnlyContent(
             recipeUiState = recipeUiState,
             onRecipeCardPressed = onRecipeCardPressed,
             modifier = Modifier.weight(1f)
                 .padding(horizontal = dimensionResource(R.dimen.recipe_list_only_horizontal_padding))
                 .padding(top = dimensionResource(R.dimen.detail_subject_padding_end))
         )
        }
    }
}





