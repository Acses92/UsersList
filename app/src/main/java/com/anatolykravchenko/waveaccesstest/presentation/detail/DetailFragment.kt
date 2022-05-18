package com.anatolykravchenko.waveaccesstest.presentation.detail

import android.annotation.SuppressLint
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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.detail_fragment) {
    private val binding by viewBinding(DetailFragmentBinding::bind)
    private var user: UserItemUi? = null
    private val viewModel by viewModels<DetailFragmentViewModel>()
    private lateinit var friends: MutableList<UserItemUi>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = arguments?.getParcelable(DETAIL_KEY)
        setupOpenFriend()
        setupTextView()
        backButtonPress()
        emailClickListener()
        phoneClickListener()
        coordinateClickListener()
        setupDateFormat()
        eyeColorImageSetup()
        favoriteFruitImageSetup()
        coordinateSetup()
        friendRecyclerViewSetup()
    }



    val user1: UserItemUi = UserItemUi("fd", "asd", 14, "das", "sad",
    "sad", "fds", "dsa", "asd", "sad", "asd", 13, false,
    41.154, 145.142, "alex", "asdf", "2016-02-14T09:26:27 -03:00", emptyList())

    val user2: UserItemUi = UserItemUi("fd", "asd", 14, "das", "sad",
        "sad", "fds", "dsa", "asd", "sad", "asd", 13, true,
        41.154, 145.142, "alex", "asdf", "2016-02-14T09:26:27 -03:00", emptyList())

    private fun friendRecyclerViewSetup() {
        val friendListAdapter = FriendsListAdapter(viewModel::onFriendClicked)
        with(binding.friendsRecyclerView) {
            adapter = friendListAdapter
            layoutManager = LinearLayoutManager(context)
            val firstFriendId = user!!.friends.substringBefore(",").filter {
                it.isDigit() }.toInt()
            val secondFriendId = user!!.friends.substringAfter(",").filter {
                it.isDigit()}.toInt()
            val firstFriend = viewModel.getFriends(firstFriendId)
            val secondFriend = viewModel.getFriends(secondFriendId)
        }
    }

    private fun setupTextView() {
        binding.userNameDetailValueTv.text = user?.name
        binding.userAgeDetailValueTv.text = user?.age.toString()
        binding.userCompanyDetailValueTv.text = user?.friends
        binding.userEmailDetailValueTv.text = user?.email
        binding.userPhoneDetailValueTv.text = user?.phone
        binding.userAddressDetailValueTv.text = user?.address
        binding.userAboutDetailValueTv.text = user?.about
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDateFormat() {
        val oldFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z")
        val date = LocalDateTime.parse(user?.registered, oldFormatter)
        val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
        binding.userRegisteredDetailValueTv.text = formatter.format(date)
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

    @SuppressLint("SetTextI18n")
    private fun coordinateSetup() {
        val latitude = user?.latitude?.let {
            BigDecimal(it).setScale(4, RoundingMode.DOWN)
        }
        val longitude = user?.longitude?.let {
            BigDecimal(it).setScale(4, RoundingMode.DOWN)
        }
        binding.userCoordinatesDetailValueTv.text = latitude.toString() + " " + longitude.toString()
    }

    private fun coordinateClickListener() {
        val latitude = user?.latitude?.let {
            BigDecimal(it).setScale(4, RoundingMode.DOWN)
        }
        val longitude = user?.longitude?.let {
            BigDecimal(it).setScale(4, RoundingMode.DOWN)
        }
        binding.userCoordinatesDetailValueTv.setOnClickListener {
            val gpsIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gpsIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            //Добавить проверку есть ли карты
            startActivity(mapIntent)
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

    private fun setupOpenFriend() {
        viewModel.openFriend.observe(viewLifecycleOwner) {
            openFriendDetail(it)
        }
    }



    private fun openFriendDetail(friend: UserItemUi) {
        val fragment = newInstance(friend)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, "DetailFragment")
            .addToBackStack(null)
            .commit()
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