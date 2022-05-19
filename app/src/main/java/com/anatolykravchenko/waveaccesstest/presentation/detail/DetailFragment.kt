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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {
    private val binding by viewBinding(DetailFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friendRecyclerViewSetup()
        setupOpenFriend()
        setupTextView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupDateFormat()
        }
        eyeColorImageSetup()
        backButtonPress()
        emailClickListener()
        phoneClickListener()
        coordinateClickListener()
        favoriteFruitImageSetup()
        coordinateSetup()
    }

    @Inject
    lateinit var detailFragmentViewModel: DetailFragmentViewModel.Factory

    private val viewModel by viewModels<DetailFragmentViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    detailFragmentViewModel.create(arguments?.getParcelable(DETAIL_KEY)!!) as T

        }
    }

    private fun friendRecyclerViewSetup() {
        val friendListAdapter = FriendsListAdapter(viewModel::onFriendClicked)
        with(binding.friendsRecyclerView) {
            adapter = friendListAdapter
            layoutManager = LinearLayoutManager(context)
            viewModel.friend.observe(viewLifecycleOwner) {
                       friendListAdapter.submitList(it)
            }
        }
    }

    private fun setupTextView() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            binding.userNameDetailValueTv.text = user.name
            binding.userAgeDetailValueTv.text = user.age.toString()
            binding.userCompanyDetailValueTv.text = user.company
            binding.userEmailDetailValueTv.text = user.email
            binding.userPhoneDetailValueTv.text = user.phone
            binding.userAddressDetailValueTv.text = user.address
            binding.userAboutDetailValueTv.text = user.about
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDateFormat() {
        val oldFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z")
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            val date = LocalDateTime.parse(user.registered, oldFormatter)
            val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
            binding.userRegisteredDetailValueTv.text = formatter.format(date)
        }
    }


    private fun eyeColorImageSetup() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            when {
                user.eyeColor.contains("green") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.green_eye_round)
                user.eyeColor.contains("blue") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.blue_eye_round)
                user.eyeColor.contains("brown") ->
                    binding.eyeColorImangeView.setImageResource(R.drawable.brown_eye_round)
            }
        }
    }

    private fun favoriteFruitImageSetup() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            when {
                user.favoriteFruit.contains("banana") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_banana_draw)
                user.favoriteFruit.contains("strawberry") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_strawberry_draw)
                user.favoriteFruit.contains("apple") ->
                    binding.favoriteFruiteImageView.setImageResource(R.drawable.ic_apple_draw)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun coordinateSetup() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            val latitude = user.latitude.let {
                BigDecimal(it).setScale(4, RoundingMode.DOWN)
            }
            val longitude = user.longitude.let {
                BigDecimal(it).setScale(4, RoundingMode.DOWN)
            }
            binding.userCoordinatesDetailValueTv.text =
                    "$latitude $longitude"
        }
    }

    private fun coordinateClickListener() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            val latitude = user.latitude.let {
                BigDecimal(it).setScale(4, RoundingMode.DOWN)
            }
            val longitude = user.longitude.let {
                BigDecimal(it).setScale(4, RoundingMode.DOWN)
            }
            binding.userCoordinatesDetailValueTv.setOnClickListener {
                val gpsIntentUri = Uri.parse("geo:$latitude,$longitude")
                val mapIntent = Intent(Intent.ACTION_VIEW, gpsIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }


    private fun emailClickListener() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            binding.userEmailDetailValueTv.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, user.email)
                    putExtra(Intent.EXTRA_SUBJECT, "Hello")
                }
                startActivity(intent)
            }
        }
    }

    private fun phoneClickListener() {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            binding.userPhoneDetailValueTv.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + user.phone)
                startActivity(intent)
            }
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

    companion object {
        private const val DETAIL_KEY = "USER"
        fun newInstance(user: UserItemUi): Fragment {
            val arg = Bundle()
            arg.putParcelable(DETAIL_KEY, user)
            val fragment = DetailFragment()
            fragment.arguments = arg
            return fragment
        }
    }
}