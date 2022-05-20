package com.anatolykravchenko.waveaccesstest.presentation.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.UserListFragmentBinding
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Success
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Error
import com.anatolykravchenko.waveaccesstest.presentation.list.UserState.Loading
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.presentation.detail.DetailFragment


@AndroidEntryPoint
class UserListFragment: Fragment(R.layout.user_list_fragment) {
    private val binding by viewBinding(UserListFragmentBinding::bind)
    private val viewModel by viewModels<UserListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupOpenDetail()
        swipeContainerListener()
        refreshButtonListener()
    }

    private fun refreshButtonListener() {
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
    }

    private fun swipeContainerListener() {
        binding.swipeContainer.setOnRefreshListener {
            viewModel.onRefresh()
            binding.swipeContainer.isRefreshing = false
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupAdapter() {
        val userListAdapter = UserListAdapter(viewModel::onUserClicked)
        with(binding.usersList) {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
            val decorator = DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
            decorator.setDrawable(resources.getDrawable(R.drawable.divider))
            addItemDecoration(decorator)
        }

        viewModel.screenState.observe(viewLifecycleOwner) {state: UserState? ->
            when(state) {
                is Error -> {
                    binding.usersList.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.stateErrorTextView.isVisible = true
                    binding.refreshButton.isVisible = true
                }
                is Success ->{
                    binding.usersList.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.stateErrorTextView.isVisible = false
                    binding.refreshButton.isVisible = false
                    userListAdapter.submitList(state.users)
                }
                is Loading -> {
                    binding.usersList.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.stateErrorTextView.isVisible = false
                    binding.refreshButton.isVisible = false
                }
            }
        }
    }

    private fun setupOpenDetail() {
        viewModel.openUser.observe(viewLifecycleOwner) {
            openUserDetail(it)
        }
    }

    private fun openUserDetail(user: UserItemUi) {
        val fragment = DetailFragment.newInstance(user)
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_fragment_container, fragment, "DetailFragment")
            .addToBackStack(null)
            .commit()
    }
}