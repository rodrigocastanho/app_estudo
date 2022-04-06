package com.estudo.appestudo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.estudo.appestudo.databinding.UiMainBinding
import com.estudo.appestudo.di.MyApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: UiMainBinding
    @Inject lateinit var viewModelFactory: ViewModelGenericFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appContainer.inject(this)
        super.onCreate(savedInstanceState)
        binding = UiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: WordsTranslatedViewModel by viewModels {
            viewModelFactory
        }

        binding.btTranslate.setOnClickListener {
            viewModel.translate(binding.edText.text.toString())
        }

        viewModel.textTranslate.observe(this, { word ->
            binding.tvWordTranslate.text = word
        })

    }

}

