package com.dbab.composesearchviewsample.searchview


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import com.dbab.composesearchviewsample.NavPath
import com.dbab.composesearchviewsample.R
import com.dbab.composesearchviewsample.User


@Composable
fun SearchViewUIDemo(
    navHostController: NavHostController,
    searchViewDemoViewModel: SearchViewDemoViewModel
) {
    val users by searchViewDemoViewModel.users.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {

        SearchViewDemoTopAppBar(onSearchBarClick = {
            navHostController.navigate(route = NavPath.UserSearch.route)
        })

        Text(
            "Search View Demo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )


        Users(users = users) { user ->
            navHostController.navigate(route = "${NavPath.UserDetail.route}?id=${user.id}")
        }

    }
}

@Composable
fun SearchViewDemoTopAppBar(onSearchBarClick: () -> Unit) {
    TopAppBar(title = { Text("Users") }, actions = {
        IconButton(
            modifier = Modifier,
            onClick = { onSearchBarClick() }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.icn_search_view_demo_app_bar_search)
            )
        }
    })
}


@Composable
fun Users(users: List<User>?, onClick: (User) -> Unit) {
    users?.forEach { user ->
        UserRow(user = user) {
            onClick(user)
        }
        Divider()
    }
}


@Composable
fun UserRow(user: User, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        Text(user.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(user.email)
        Spacer(modifier = Modifier.height(4.dp))
    }
}