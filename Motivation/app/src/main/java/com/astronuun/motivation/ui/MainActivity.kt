package com.astronuun.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.astronuun.motivation.R
import com.astronuun.motivation.data.Mock
import com.astronuun.motivation.infra.SecurityPreferences
import com.astronuun.motivation.databinding.ActivityMainBinding
import com.astronuun.motivation.infra.MotivationConstants

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUserName()
        handleFilter(R.id.image_all_inclusive)
        handleNextPhrase()

        // Events
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageEmoji.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_emoji, R.id.image_sunny, R.id.image_all_inclusive)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.textMotivationPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageEmoji.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_emoji -> {
                binding.imageEmoji.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString("USER_NAME")
        binding.textUserName.text = "Olá $name!"
    }
}