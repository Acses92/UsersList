package com.anatolykravchenko.waveaccesstest.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.presentation.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val userLocalRepository: UserLocalRepository
) : ViewModel() {
    private val _openFriend = SingleLiveEvent<UserItemUi>()
    val openFriend: LiveData<UserItemUi> = _openFriend


    fun onFriendClicked(user: UserItemUi) {
        _openFriend.value = user
    }


}