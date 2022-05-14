package com.anatolykravchenko.waveaccesstest.presentation.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.DetailFragmentBinding
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.detail_fragment) {
    private val binding by viewBinding(DetailFragmentBinding::bind)
    private var user: UserItemUi? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = arguments?.getParcelable(DETAIL_KEY)
        setupUi()
        backButtonPress()
        emailClickListener()
    }

    private fun setupUi() {
        binding.userNameDetailValueTv.text = user?.name
        binding.userAgeDetailValueTv.text = user?.age.toString()
        binding.userCompanyDetailValueTv.text = user?.company
        binding.userEmailDetailValueTv.text = user?.email
        binding.userPhoneDetailValueTv.text = user?.phone
        binding.userAddressDetailValueTv.text = user?.address
        binding.userAboutDetailValueTv.text = user?.about
        binding.userRegisteredDetailValueTv.text = user?.registered
        val eyeColor= user?.eyeColor
        if (eyeColor != null) {
            when {
                eyeColor.contains("green") -> binding.eyeColorImangeView.setImageResource(R.drawable.green_eye_round)
                eyeColor.contains("blue") ->binding.eyeColorImangeView.setImageResource(R.drawable.blue_eye_round)
                eyeColor.contains("brown") -> binding.eyeColorImangeView.setImageResource(R.drawable.brown_eye_round)
                else ->binding.eyeColorImangeView.setImageLevel(R.drawable.brown_eye_round)
            }
        }
    }

    private fun emailClickListener() {
        binding.userEmailDetailValueTv.setOnClickListener {
            Toast.makeText(context, "Тык", Toast.LENGTH_LONG)
                .show()
        }
    }



    private fun backButtonPress() {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

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