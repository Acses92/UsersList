package com.anatolykravchenko.waveaccesstest.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.presentation.common.SingleLiveEvent

class DetailFragmentViewModel: ViewModel() {
    private val _openFriend = SingleLiveEvent<UserItemUi>()
    val openFriend: LiveData<UserItemUi> = _openFriend


    fun onFriendClicked(user: UserItemUi) {
        _openFriend.value = user
    }
}