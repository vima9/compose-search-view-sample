package com.dbab.composesearchviewsample.usersearch

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.dbab.composesearchviewsample.NavPath
import com.dbab.composesearchviewsample.searchview.Users
import com.dbab.composesearchviewsample.components.SearchBarUI
import kotlinx.coroutines.flow.Flow


private const val TAG="UserSearchUI"

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun UserSearchUI(navHostController: NavHostController, userSearchViewModel: UserSearchViewModel) {


    val searchLocationState by rememberFlowWithLifecycle(userSearchViewModel.userSearchModelState)
        .collectAsState(initial = UserSearchModelState.Empty)

    SearchBarUI(
        searchText = searchLocationState.searchText,
        placeholderText = "Search users",
        onSearchTextChanged = { userSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { userSearchViewModel.onClearClick() },
        onNavigateBack = {
            navHostController.popBackStack()
        },
        matchesFound = searchLocationState.users.isNotEmpty()
    ) {

        Users(users = searchLocationState.users) { user ->
            navHostController.navigate(route = "${NavPath.UserDetail.route}?id=${user.id}")
        }

    }


}


@Composable
fun <T> rememberFlowWithLifecycle(
    flow: Flow<T>,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> = remember(flow, lifecycle) {
    flow.flowWithLifecycle(
        lifecycle = lifecycle,
        minActiveState = minActiveState
    )
}
