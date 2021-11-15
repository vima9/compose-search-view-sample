package com.dbab.composesearchviewsample.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dbab.composesearchviewsample.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) :
    ViewModel() {

    private val userId = savedStateHandle.get<Long>("id")
    val user = userRepository.getUser(userId)!!


}