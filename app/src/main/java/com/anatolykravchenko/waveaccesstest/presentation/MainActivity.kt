package com.anatolykravchenko.waveaccesstest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.databinding.ActivityMainBinding
import com.anatolykravchenko.waveaccesstest.presentation.list.UserListFragment
import com.anatolykravchenko.waveaccesstest.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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