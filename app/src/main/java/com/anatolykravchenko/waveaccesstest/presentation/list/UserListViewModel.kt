package com.anatolykravchenko.waveaccesstest.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.anatolykravchenko.waveaccesstest.domain.UserNetworkRepository
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.presentation.common.SingleLiveEvent
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Loading
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Error
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Success
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userNetworkRepository: UserNetworkRepository,
    private val userLocalRepository: UserLocalRepository): ViewModel() {

    private val _openUser = SingleLiveEvent<UserItemUi>()
    val openUser: LiveData<UserItemUi> = _openUser

    private val _screenState = MutableLiveData<UserState>(Loading())
    val screenState:LiveData<UserState> = _screenState

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            try {
                var users: List<UserItemUi> = userLocalRepository.getAll()
                if(users.isEmpty()) {
                    loadFromServerToDb()
                    users = userLocalRepository.getAll()
                }
                _screenState.value = Success(users)
            } catch (error: Throwable) {
                _screenState.value = Error(error)
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            try {
                val users: List<UserItemUi> = userNetworkRepository.getUserList().map {
                    it.toUserItemUi() }
                loadFromServerToDb()
                _screenState.value = Success(users)
            } catch(error: Throwable) {
                _screenState.value = Error(error)
            }
        }
    }

    private suspend fun loadFromServerToDb() {
        val localUserSet = userLocalRepository.getAll()
        val newUserSet: List<UserItemUi> = userNetworkRepository.getUserList().map { it.toUserItemUi() }
            .filterNot{
                localUserSet
                    .contains(it)
            }
        userLocalRepository.insert(newUserSet)
    }

    fun onUserClicked(user: UserItemUi) {
        _openUser.value = user
    }

}

sealed class UserState {
    class Loading(): UserState()
    class Success(val users: List<UserItemUi>): UserState()
    class Error(val throwable: Throwable): UserState()
}