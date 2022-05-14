package com.anatolykravchenko.waveaccesstest.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.UserListFragmentBinding
import com.anatolykravchenko.waveaccesstest.presentation.adapters.UserListAdapter

@AndroidEntryPoint
class UserListFragment: Fragment(R.layout.user_list_fragment) {
    private val binding by viewBinding(UserListFragmentBinding::bind)
    private val viewModel by viewModels<UserListViewModel>()
    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userListAdapter = UserListAdapter(viewModel::onUserClicked)
        with(binding.usersList) {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}