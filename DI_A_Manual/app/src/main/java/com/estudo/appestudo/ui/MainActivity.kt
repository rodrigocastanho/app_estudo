package com.estudo.appestudo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.estudo.appestudo.databinding.UiMainBinding
import com.estudo.appestudo.di.MyApplication

class MainActivity : AppCompatActivity() {

    private lateinit var binding: UiMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appContainer = (application as MyApplication).appContainer

        val viewModel: WordsTranslatedViewModel by viewModels {
                WordsTranslatedViewModelFactory(appContainer.wordsTranslatedUseCase)
        }

        binding.btTranslate.setOnClickListener {
            viewModel.translate(binding.edText.text.toString())
        }

        viewModel.textTranslate.observe(this, { word ->
            binding.tvWordTranslate.text = word
        })


    }

}

