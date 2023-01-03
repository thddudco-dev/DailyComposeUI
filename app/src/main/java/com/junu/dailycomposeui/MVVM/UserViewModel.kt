package com.junu.dailycomposeui.MVVM

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    private  val _users = mutableStateListOf<User>()

    val users: List<User>
        get() = _users

    fun addUser(user: User) {
        _users.add(user)
    }
}