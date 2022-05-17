package com.anatolykravchenko.waveaccesstest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.databinding.FreindItemBinding


class FriendsListAdapter(private val onIteClicked: (UserItemUi) -> Unit ):
    ListAdapter<UserItemUi, FriedViewHolder>(
        object: DiffUtil.ItemCallback<UserItemUi>() {
            override fun areContentsTheSame(oldItem: UserItemUi, newItem: UserItemUi): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: UserItemUi, newItem: UserItemUi): Boolean =
                    oldItem.name ==newItem.name
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriedViewHolder {
        return FriedViewHolder(
            FreindItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FriedViewHolder, position: Int) {
        with(holder.binding) {
            val item = getItem(position)
            userNameViewHolder.text = item.name
            userEmailViewHolder.text = item.email
            if(item.isActive) {
                isActiveStatusViewHolder.isVisible = true
                root.setOnClickListener { onIteClicked(item) }
            }
        }
    }
}