package com.anatolykravchenko.waveaccesstest.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.UserListFragmentBinding
import com.anatolykravchenko.waveaccesstest.presentation.adapters.UserListAdapter
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Success
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Error
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Loading


@AndroidEntryPoint
class UserListFragment: Fragment(R.layout.user_list_fragment) {
    private val binding by viewBinding(UserListFragmentBinding::bind)
    private val viewModel by viewModels<UserListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userListAdapter = UserListAdapter(viewModel::onUserClicked)
        with(binding.usersList) {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.screenState.observe(viewLifecycleOwner) {state: UserState? ->
            when(state) {
                is Error -> {
                    binding.usersList.isVisible = false
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG)
                        .show()
                }
                is Success ->{
                    binding.usersList.isVisible = true
                    userListAdapter.submitList(state.users)
                    Toast.makeText(context, "Всё ок", Toast.LENGTH_LONG)
                        .show()
                }
                is Loading -> {
                    binding.usersList.isVisible = false
                }
            }

        }
    }
}