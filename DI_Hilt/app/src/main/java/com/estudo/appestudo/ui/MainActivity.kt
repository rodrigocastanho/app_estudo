package com.estudo.appestudo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.estudo.appestudo.databinding.UiMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: UiMainBinding
    private val viewModel by viewModels<WordsTranslatedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btTranslate.setOnClickListener {
            viewModel.translate(binding.edText.text.toString())
        }

        viewModel.textTranslate.observe(this, { word ->
            binding.tvWordTranslate.text = word
        })
    }
}

