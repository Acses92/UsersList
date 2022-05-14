package com.anatolykravchenko.waveaccesstest.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.DetailFragmentBinding
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.detail_fragment) {
    private val binding by viewBinding(DetailFragmentBinding::bind)

    companion object {
        private const val DETAIL_KEY = "USER"
        fun newInstance(user: UserItemUi): Fragment{
            val arg = Bundle()
            arg.putParcelable(DETAIL_KEY, user)
            val fragment = DetailFragment()
            fragment.arguments = arg
            return fragment
        }
    }
}