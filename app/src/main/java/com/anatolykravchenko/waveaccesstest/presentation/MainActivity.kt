package com.anatolykravchenko.waveaccesstest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.databinding.ActivityMainBinding
import com.anatolykravchenko.waveaccesstest.presentation.list.UserListFragment
import com.anatolykravchenko.waveaccesstest.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeFragmentSetup()
    }

    private fun homeFragmentSetup() {
        val userListFragment = UserListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, userListFragment, "USER_LIST_FRAGMENT")
            .commit()
    }
}