package com.dbab.composesearchviewsample.usersearch

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.dbab.composesearchviewsample.NavPath
import com.dbab.composesearchviewsample.searchview.Users
import com.dbab.composesearchviewsample.components.SearchBarUI
import com.dbab.composesearchviewsample.rememberFlowWithLifecycle


private const val TAG="UserSearchUI"

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun UserSearchUI(navHostController: NavHostController, userSearchViewModel: UserSearchViewModel) {
    val userSearchModelState by rememberFlowWithLifecycle(userSearchViewModel.userSearchModelState)
        .collectAsState(initial = UserSearchModelState.Empty)
    SearchBarUI(
        searchText = userSearchModelState.searchText,
        placeholderText = "Search users",
        onSearchTextChanged = { userSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { userSearchViewModel.onClearClick() },
        onNavigateBack = {
            navHostController.popBackStack()
        },
        matchesFound = userSearchModelState.users.isNotEmpty()
    ) {

        Users(users = userSearchModelState.users) { user ->
            navHostController.navigate(route = "${NavPath.UserDetail.route}?id=${user.id}")
        }
    }
}

