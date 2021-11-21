package com.dbab.composesearchviewsample.usersearch

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dbab.composesearchviewsample.User
import com.dbab.composesearchviewsample.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.ArrayList
import javax.inject.Inject

private const val TAG = "SearchViewDemoViewModel"

@HiltViewModel
class UserSearchViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private var allUsers: ArrayList<User> = ArrayList<User>()
    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var matchedUsers: MutableStateFlow<List<User>> =
        MutableStateFlow(arrayListOf())

    val userSearchModelState = combine(
        searchText,
        matchedUsers,
        showProgressBar
    ) { text, matchedUsers, showProgress ->

        UserSearchModelState(
            text,
            matchedUsers,
            showProgress
        )
    }


    init {
        retrieveUsers()
    }

    fun retrieveUsers() {
        val users = userRepository.getUsers()

        if (users != null) {
            allUsers.addAll(users)
        }

    }



    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedUsers.value = arrayListOf()
            return
        }
        val usersFromSearch = allUsers.filter { x ->
            x.username.contains(changedSearchText, true) ||
                    x.email.contains(changedSearchText, true) || x.name.contains(
                changedSearchText,
                true
            )
        }
        matchedUsers.value = usersFromSearch
    }

    fun onClearClick() {
        searchText.value = ""
        matchedUsers.value = arrayListOf()
    }



}

