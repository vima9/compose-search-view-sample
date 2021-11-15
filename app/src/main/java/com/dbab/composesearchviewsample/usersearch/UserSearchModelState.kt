package com.dbab.composesearchviewsample.usersearch

import com.dbab.composesearchviewsample.User


data class UserSearchModelState(
    val searchText: String = "",
    val users: List<User> = arrayListOf(),
    val showProgressBar: Boolean = false
) {

    companion object {
        val Empty = UserSearchModelState()
    }

}