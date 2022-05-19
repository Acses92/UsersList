package com.anatolykravchenko.waveaccesstest.presentation.detail

import android.icu.util.Freezable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anatolykravchenko.waveaccesstest.data.model.Friend
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.presentation.common.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


class DetailFragmentViewModel @AssistedInject constructor(
    @Assisted private val user: UserItemUi,
    private val userLocalRepository: UserLocalRepository
) : ViewModel() {

    @AssistedFactory
    interface Factory{
        fun create(user: UserItemUi): DetailFragmentViewModel
    }

    init {
        getFriend(user.friends)
    }

    private val _openFriend = SingleLiveEvent<UserItemUi>()
    val openFriend: LiveData<UserItemUi> = _openFriend

    private val _userState = MutableLiveData(user)
    val userState:LiveData<UserItemUi> = _userState

    private val _friend = MutableLiveData<List<UserItemUi>>()
    val friend:LiveData<List<UserItemUi>> = _friend

    fun onFriendClicked(user: UserItemUi) {
        _openFriend.value = user
    }

    private fun getFriend(friend: List<Friend>) {
        viewModelScope.launch {
           val friend = friend.map { userLocalRepository.getUserById(it.id) }
            _friend.value = friend
        }
    }

}