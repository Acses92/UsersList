package com.anatolykravchenko.waveaccesstest.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.anatolykravchenko.waveaccesstest.R
import com.anatolykravchenko.waveaccesstest.databinding.DetailFragmentBinding
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        phoneClickListener()
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
      //  dataFormatter()
        eyeColorImageSetup()
        favoriteFruitImageSetup()
    }

    private fun dataFormatter() {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss SSSXXX" )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val data = user?.registered
        binding.userRegisteredDetailValueTv.text =
                LocalDate.parse(data, formatter).toString()
    }

    private fun eyeColorImageSetup() {
        val eyeColor= user?.eyeColor
        if (eyeColor != null) {
            when {
                eyeColor.contains("green") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.green_eye_round)
                eyeColor.contains("blue") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.blue_eye_round)
                eyeColor.contains("brown") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.brown_eye_round)
                else ->binding.eyeColorImangeView.setImageLevel(R.drawable.brown_eye_round)
            }
        }
    }

    private fun favoriteFruitImageSetup() {
        val favoriteFruit = user?.favoriteFruit
        if(favoriteFruit != null) {
            when{
                favoriteFruit.contains("banana") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_banana_draw)
                favoriteFruit.contains("strawberry") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_strawberry_draw)
                favoriteFruit.contains("apple") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_apple_draw)
            }
        }
    }



    private fun emailClickListener() {
        binding.userEmailDetailValueTv.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, user?.email)
                putExtra(Intent.EXTRA_SUBJECT, "Hello")
            }
            //добавить проверку есть ли отправка сообщений
            startActivity(intent)
        }
    }

    private fun phoneClickListener() {
        binding.userPhoneDetailValueTv.setOnClickListener {
            //добавить проверку, есть ли доступ к телефону
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+ user?.phone)
            startActivity(intent)
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