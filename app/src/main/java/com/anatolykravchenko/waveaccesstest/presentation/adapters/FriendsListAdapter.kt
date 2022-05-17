package com.anatolykravchenko.waveaccesstest.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi


class FriendsListAdapter(private val onIteClicked: (UserItemUi) -> Unit ):
    ListAdapter<UserItemUi, UserViewHolder>(
        object: DiffUtil.ItemCallback<UserItemUi>() {
            override fun areContentsTheSame(oldItem: UserItemUi, newItem: UserItemUi): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: UserItemUi, newItem: UserItemUi): Boolean =
                    oldItem.name ==newItem.name
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}