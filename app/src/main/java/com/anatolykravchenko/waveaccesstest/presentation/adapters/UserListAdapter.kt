package com.anatolykravchenko.waveaccesstest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.databinding.UserItemBinding

class UserListAdapter(private val onItemClicked: (UserItemUi) ->Unit):
    RecyclerView.Adapter<UserViewHolder>() {

    val myData = mutableListOf<UserItemUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
        UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = myData[position]
        holder.apply {
            binding.userName.text = user.name
            binding.userEmail.text = user.email
            if(user.isActive) {
                binding.isActiveStatsuTextView.isVisible
                holder.itemView.setOnClickListener{onItemClicked(user)}
            }
        }
    }

    override fun getItemCount(): Int = myData.size

    fun addUser(list: List<UserItemUi>) = myData.addAll(list)
}