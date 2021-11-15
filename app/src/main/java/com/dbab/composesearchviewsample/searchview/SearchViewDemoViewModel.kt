package com.dbab.composesearchviewsample.searchview

import androidx.lifecycle.ViewModel
import com.dbab.composesearchviewsample.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewDemoViewModel @Inject constructor(userRepository: UserRepository) :
    ViewModel() {


    val users = MutableStateFlow(userRepository.getUsers())






}