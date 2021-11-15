package com.dbab.composesearchviewsample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.dbab.composesearchviewsample.usersearch.UserSearchUI
import com.dbab.composesearchviewsample.usersearch.UserSearchViewModel
import com.dbab.composesearchviewsample.searchview.SearchViewDemoViewModel
import com.dbab.composesearchviewsample.searchview.SearchViewUIDemo
import com.dbab.composesearchviewsample.userdetail.UserDetailUI
import com.dbab.composesearchviewsample.userdetail.UserDetailViewModel

enum class NavPath(
    val route: String,
) {
    SearchViewDemo(route = "search_view_demo"),
    UserSearch(route = "user_search"),
    UserDetail(route = "user_detail")
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController, scaffoldState: ScaffoldState) {

    NavHost(
        navController = navHostController,
        startDestination = NavPath.SearchViewDemo.route
    ) {

        composable(NavPath.SearchViewDemo.route) {
            val searchDemoViewModel = hiltViewModel<SearchViewDemoViewModel>()

            SearchViewUIDemo(
                navHostController = navHostController,
                searchViewDemoViewModel = searchDemoViewModel
            )
        }



        composable(NavPath.UserSearch.route) {
            val userSearchDemoViewModel = hiltViewModel<UserSearchViewModel>()

            UserSearchUI(
                navHostController = navHostController,
                userSearchViewModel = userSearchDemoViewModel
            )

        }


        composable(
            "${NavPath.UserDetail.route}?id={id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                })
        ) {

            val userDetailViewModel = hiltViewModel<UserDetailViewModel>()
            UserDetailUI(
                navHostController = navHostController,
                userDetailViewModel = userDetailViewModel
            )

        }

    }

}