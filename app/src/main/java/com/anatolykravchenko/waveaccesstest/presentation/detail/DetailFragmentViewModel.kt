package com.anatolykravchenko.waveaccesstest.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anatolykravchenko.waveaccesstest.data.model.Friend
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.presentation.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val userLocalRepository: UserLocalRepository
) : ViewModel() {

    private val _openFriend = SingleLiveEvent<UserItemUi>()
    val openFriend: LiveData<UserItemUi> = _openFriend

    private val _friend = MutableLiveData<List<UserItemUi>>()
    val friend:LiveData<List<UserItemUi>> = _friend

    fun onFriendClicked(user: UserItemUi) {
        _openFriend.value = user
    }

    fun getFriend(friend: List<Friend>) {
        viewModelScope.launch {
            val friend = friend.map { userLocalRepository.getUserById(it.id) }
            _friend.value = friend
        }
    }

}